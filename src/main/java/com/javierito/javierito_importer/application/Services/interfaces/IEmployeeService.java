package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.EmployeeModels.Employee;
import com.javierito.javierito_importer.domain.models.EmployeeModels.Manager;

import java.util.List;

public interface IEmployeeService {
    Employee getByUserId(long userId);
    List<Manager> getManagers();
}
