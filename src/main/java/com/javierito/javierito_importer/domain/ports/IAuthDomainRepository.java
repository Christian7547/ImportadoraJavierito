package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.userModels.User;

public interface IAuthDomainRepository {
    User authenticate(String username, String password);
    User resetPassword(User userWithNewPassword);
}
