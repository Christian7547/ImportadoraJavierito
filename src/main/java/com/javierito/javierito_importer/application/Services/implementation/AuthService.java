package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IAuthService;
import com.javierito.javierito_importer.domain.models.userModels.User;
import com.javierito.javierito_importer.domain.ports.IAuthDomainRepository;
import com.javierito.javierito_importer.domain.ports.IUserDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IAuthDomainRepository authDomainRepository;
    private final IUserDomainRepository userDomainRepository;

    @Override
    public User authenticate(String username, String password) {
        return authDomainRepository.authenticate(username, password);
    }

    @Override
    public User resetPassword(String email, String newPassword) {
        User getUser = userDomainRepository.getByEmail(email);
        if(getUser != null){
            String hash = new BCryptPasswordEncoder().encode(newPassword);
            getUser.setPassword(hash);
            getUser.setLastUpdate(LocalDateTime.now());
            return authDomainRepository.resetPassword(getUser);
        }
        return null;
    }
}
