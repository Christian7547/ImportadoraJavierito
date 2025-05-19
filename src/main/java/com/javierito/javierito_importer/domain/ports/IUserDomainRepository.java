package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.userModels.User;
import com.javierito.javierito_importer.domain.models.userModels.UserList;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IUserDomainRepository {
    List<UserList> getAll(Pageable pageable, @Nullable Short status, @Nullable String role, @Nullable Integer officeId, @Nullable String someName);
    User saveUser(User user);
    User getByEmail(String email);
    User getById(long id);
    long countUsers();
    long countActiveUsers();
    long countInactiveUsers();
}
