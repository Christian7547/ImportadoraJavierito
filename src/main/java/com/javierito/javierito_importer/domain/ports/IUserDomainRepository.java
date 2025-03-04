package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.userModels.User;
import com.javierito.javierito_importer.domain.models.userModels.UserList;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserDomainRepository {
    List<UserList> getAll(Pageable pageable);
    User createUser(User user);
    User getByEmail(String email);
    User getById(long id);
    void removeUser(User user);
}
