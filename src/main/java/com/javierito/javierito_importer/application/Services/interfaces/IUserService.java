package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.EmployeeModels.Employee;
import com.javierito.javierito_importer.domain.models.userModels.User;
import com.javierito.javierito_importer.domain.models.userModels.UserList;
import org.springframework.data.util.Pair;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IUserService {
    List<UserList> getAll(int page, int size, @Nullable Short status, @Nullable String role, @Nullable Integer officeId, @Nullable String someName);
    User createUser(User user, Employee employee);
    boolean updateUser(User user, Employee employee);
    long changeStatus(long id, short newStatus);
    Pair<User, String> getByEmail(String email);
    User getById(long id);
    long countUsers();
    long countActiveUsers();
    long countInactiveUsers();
}
