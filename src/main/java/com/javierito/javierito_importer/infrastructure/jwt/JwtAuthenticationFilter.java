package com.javierito.javierito_importer.infrastructure.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            if (isPublicPath(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwt = extractJwtToken(request);
            if (jwt == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String userName = jwtService.extractUsername(jwt);
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                processUserAuthentication(request, jwt, userName);
            }

            filterChain.doFilter(request, response);
        } catch (MalformedJwtException e) {
            response.getWriter().write("Malformed JWT: " + e.getMessage());
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(e.getMessage());
        }
    }

    private boolean isPublicPath(HttpServletRequest request) {
        return request.getServletPath().contains("/api/auth");
    }

    private String extractJwtToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7);
    }

    private void processUserAuthentication(HttpServletRequest request, String jwt, String userId) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);

        if (jwtService.isTokenValid(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authentication = createAuthenticationToken(userDetails, request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private UsernamePasswordAuthenticationToken createAuthenticationToken(
            UserDetails userDetails,
            HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }
}
