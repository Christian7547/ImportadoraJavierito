package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.Employee;
import com.javierito.javierito_importer.domain.models.User;

public interface IUserService {
    User createEmployeeUser(User user, Employee employee);
    void removeUser(User user);
}
