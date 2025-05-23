package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IEmployeeService;
import com.javierito.javierito_importer.infrastructure.exception.types.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;

    @GetMapping("/getManagers")
    public ResponseEntity<?> getManagers() {
        var managers = employeeService.getManagers();
        if(managers.isEmpty()) {
            throw new ResourceNotFoundException("managers");
        }
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }
}
