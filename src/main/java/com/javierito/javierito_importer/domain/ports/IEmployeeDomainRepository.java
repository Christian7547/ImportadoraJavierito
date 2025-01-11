package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.Employee;

public interface IEmployeeDomainRepository {
    Employee createEmployee(Employee employee);
    void removeEmployee(Employee employee);
}
