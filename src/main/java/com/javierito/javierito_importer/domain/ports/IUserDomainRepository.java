package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserDomainRepository {
    List<User> getAll(Pageable pageable);
    User createUser(User user);
    User getByEmail(String email);
    void removeUser(User user);
}
