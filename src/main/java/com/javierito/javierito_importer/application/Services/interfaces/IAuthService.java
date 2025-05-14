package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.userModels.User;
import org.springframework.data.util.Pair;

public interface IAuthService {
    Pair<User, String> authenticate(String username, String password);
    User resetPassword(String email, String newPassword);
    User changeFirstLogin(long userId);
}
