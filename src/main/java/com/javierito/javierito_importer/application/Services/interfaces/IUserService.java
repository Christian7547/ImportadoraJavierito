package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.Employee;
import com.javierito.javierito_importer.domain.models.userModels.User;
import com.javierito.javierito_importer.domain.models.userModels.UserList;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IUserService {
    List<UserList> getAll(int page, int size);
    User createUser(User user, Employee employee);
    boolean updateUser(User user, Employee employee);
    Pair<User, String> getByEmail(String email);
    User getById(long id);
    long countUsers();
    void removeUser(User user);
}
