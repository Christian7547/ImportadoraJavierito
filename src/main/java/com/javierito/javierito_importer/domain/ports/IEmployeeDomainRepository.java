package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.Employee;

public interface IEmployeeDomainRepository {
    Employee createEmployee(Employee employee);
    Employee getByUserId(long userId);
    void removeEmployee(Employee employee);
}
