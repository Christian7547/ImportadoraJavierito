package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IAuthService;
import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.infrastructure.dtos.auth.LoginDTO;
import com.javierito.javierito_importer.infrastructure.dtos.auth.ResetPasswordDTO;
import com.javierito.javierito_importer.infrastructure.jwt.JwtService;
import com.javierito.javierito_importer.infrastructure.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;
    private final JwtService jwtService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDTO data){
        try
        {
            User user = authService.authenticate(data.getUsername(), data.getPassword());
            if(user != null)
            {
                var u = userMapper.toUserEntity(user);
                String token = jwtService.generateToken(u);
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception ocurred");
        }
    }

    @PostMapping
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO data){
        User user = authService.resetPassword(data.getEmail(), data.getNewPassword());
        if(user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().body("No saved changes");
    }
}
