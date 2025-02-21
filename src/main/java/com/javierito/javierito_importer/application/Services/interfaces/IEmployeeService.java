package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.Employee;

public interface IEmployeeService {
    Employee getByUserId(long userId);
}
