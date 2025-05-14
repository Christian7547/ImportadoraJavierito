package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IAuthService;
import com.javierito.javierito_importer.application.Services.interfaces.IUserService;
import com.javierito.javierito_importer.domain.models.userModels.User;
import com.javierito.javierito_importer.infrastructure.dtos.auth.LoginDTO;
import com.javierito.javierito_importer.infrastructure.dtos.auth.ResetPasswordDTO;
import com.javierito.javierito_importer.infrastructure.exception.types.BadRequestException;
import com.javierito.javierito_importer.infrastructure.exception.types.ResourceNotFoundException;
import com.javierito.javierito_importer.infrastructure.jwt.JwtService;
import com.javierito.javierito_importer.infrastructure.mappers.UserMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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
    private final IUserService userService;
    private final JwtService jwtService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginDTO loginDTO){
        Pair<User, String> data = authService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());

        if(data == null) {
            throw new ResourceNotFoundException("user", "username", loginDTO.getUsername());
        }

        var u = userMapper.toUserEntity(data.getFirst());
        String token = jwtService.generateToken(u);
        Map<String, Object> response = new HashMap<>();
        response.put("firstLogin", data.getFirst().getFirstLogin());
        response.put("token", token);
        response.put("branchOffice", data.getSecond());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getRecoveryCode")
    public ResponseEntity<?> getRecoveryCode(@RequestBody String email){
        Pair<User, String> data = userService.getByEmail(email);
        if(data == null) {
            throw new ResourceNotFoundException("user", "email", email);
        }
        return ResponseEntity.ok(data.getSecond());
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordDTO data){
        User user = authService.resetPassword(data.getEmail(), data.getNewPassword());
        if(user == null){
            throw new ResourceNotFoundException("user", "email", data.getEmail());
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/changeFirstLogin/{userId}")
    public ResponseEntity<?> changeFirstLogin(@PathVariable long userId) {
        User user = authService.changeFirstLogin(userId);
        if (user == null) {
            throw new ResourceNotFoundException("user", "id", Long.toString(userId));
        }
        return ResponseEntity.noContent().build();
    }
}
