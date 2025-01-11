package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.Employee;
import com.javierito.javierito_importer.domain.ports.IEmployeeDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IEmployeeRepository;
import com.javierito.javierito_importer.infrastructure.mappers.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository implements IEmployeeDomainRepository {

    @Autowired
    private EmployeeMapper employeeMapper;

    private final IEmployeeRepository employeeRepository;

    public EmployeeRepository(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        var toEntity = employeeMapper.toEmployeeEntity(employee);
        var employeeCreated = employeeRepository.save(toEntity);
        return employeeMapper.toEmployee(employeeCreated);
    }

    @Override
    public void removeEmployee(Employee employee) {

    }
}
