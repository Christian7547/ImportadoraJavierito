package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.EmployeeModels.Employee;
import com.javierito.javierito_importer.domain.models.EmployeeModels.Manager;

import java.util.List;

public interface IEmployeeDomainRepository {
    Employee saveEmployee(Employee employee);
    Employee getByUserId(long userId);
    List<Manager> getManagers();
}
