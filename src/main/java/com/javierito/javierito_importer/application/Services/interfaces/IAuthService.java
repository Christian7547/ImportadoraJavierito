package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.User;

public interface IAuthService {
    User authenticate(String username, String password);
    User resetPassword(String email, String newPassword);
}
