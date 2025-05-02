package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IEmployeeService;
import com.javierito.javierito_importer.application.Services.interfaces.IUserService;
import com.javierito.javierito_importer.domain.models.Employee;
import com.javierito.javierito_importer.domain.models.userModels.User;
import com.javierito.javierito_importer.domain.models.userModels.UserList;
import com.javierito.javierito_importer.infrastructure.dtos.user.AccountDTO;
import com.javierito.javierito_importer.infrastructure.dtos.user.ParamsUserDTO;
import com.javierito.javierito_importer.infrastructure.dtos.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final IEmployeeService employeeService;

    @PostMapping("/getAll")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "5")int limit,
                                    @RequestParam(defaultValue = "1")int offset,
                                    @RequestBody ParamsUserDTO paramsUserDTO){
        List<UserList> users = userService.getAll(limit, offset,
                paramsUserDTO.getStatus(),
                paramsUserDTO.getRole(),
                paramsUserDTO.getOfficeId(),
                paramsUserDTO.getSomeName());
        long total = userService.countUsers();
        Pair<List<UserList>, Long> data = Pair.of(users, total);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/saveUser")
    @PreAuthorize("hasRole('Admin')")
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

    @PatchMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody AccountDTO body){
        User user = User.builder()
                .id(body.getId())
                .email(body.getEmail())
                .build();
        Employee employee = Employee.builder()
                .userId(body.getId())
                .name(body.getName())
                .lastName(body.getLastName())
                .secondLastName(body.getSecondLastName())
                .ci(body.getCi())
                .phoneNumber(body.getPhoneNumber())
                .build();
        boolean res = userService.updateUser(user, employee);
        if(res)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/changeStatus/{userId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> changeStatus(@PathVariable long userId, @RequestParam int newStatus){
        long id = userService.changeStatus(userId, (short) newStatus);
        if(id > 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
