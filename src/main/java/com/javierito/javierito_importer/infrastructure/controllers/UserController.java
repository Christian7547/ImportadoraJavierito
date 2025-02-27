package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IEmployeeService;
import com.javierito.javierito_importer.application.Services.interfaces.IUserService;
import com.javierito.javierito_importer.domain.models.Employee;
import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.infrastructure.dtos.user.AccountDTO;
import com.javierito.javierito_importer.infrastructure.dtos.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final IEmployeeService employeeService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0")int page,
                                    @RequestParam(defaultValue = "10")int size){
        List<User> data = userService.getAll(page, size);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/getProfile/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable int id){
        User user = userService.getById(id);
        Employee employee = employeeService.getByUserId(user.getId());
        AccountDTO accountDTO = AccountDTO.builder()
                .id(user.getId())
                .name(employee.getName())
                .lastName(employee.getLastName())
                .secondLastName(employee.getSecondLastName())
                .ci(employee.getCi())
                .phoneNumber(employee.getPhoneNumber())
                .email(user.getEmail())
                .build();
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @PostMapping("/getByEmail")
    public ResponseEntity<?> getUserByEmail(@RequestBody String email){
        Pair<User, String> data = userService.getByEmail(email);
        return ResponseEntity.ok(data.getSecond());
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        User user = User.builder()
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
        var created = userService.createUser(user, employee);
        if(created != null){
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
