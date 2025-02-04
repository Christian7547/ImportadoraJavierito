package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.User;

public interface IUserDomainRepository {
    User createUser(User user);
    User getByEmail(String email);
    void removeUser(User user);
}
