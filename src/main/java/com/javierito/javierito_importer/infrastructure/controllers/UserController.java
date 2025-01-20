package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.services.interfaces.IUserService;
import com.javierito.javierito_importer.domain.models.Client;
import com.javierito.javierito_importer.domain.models.Employee;
import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.infrastructure.dtos.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createEmployeeUser")
    public ResponseEntity<User> createUserAsync(@RequestBody UserDTO userDTO){
        User user = User.builder()
                .userName(userDTO.getUserName())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .build();
        Employee employee = Employee.builder()
                .name(userDTO.getName())
                .lastName(userDTO.getLastName())
                .secondLastName(userDTO.getSecondLastName())
                .ci(userDTO.getCi())
                .phoneNumber(userDTO.getPhoneNumber())
                .branchOfficeId(userDTO.getBranchOfficeId())
                .build();
        var created = userService.createEmployeeUser(user, employee);
        if(created != null)
            return new ResponseEntity<>(created,HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
