package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.Employee;
import com.javierito.javierito_importer.domain.models.User;

import java.util.List;

public interface IUserService {
    List<User> getAll(int page, int size);
    User createUser(User user, Employee employee);
    User getByEmail(String email);
    void removeUser(User user);
}
