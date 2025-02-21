package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IEmployeeService;
import com.javierito.javierito_importer.domain.models.Employee;
import com.javierito.javierito_importer.domain.ports.IEmployeeDomainRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final IEmployeeDomainRepository employeeDomainRepository;

    @Override
    public Employee getByUserId(long userId) {
        return employeeDomainRepository.getByUserId(userId);
    }
}
