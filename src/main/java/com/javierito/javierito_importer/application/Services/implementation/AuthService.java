package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IAuthService;
import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.domain.ports.IAuthDomainRepository;

public class AuthService implements IAuthService {

    private final IAuthDomainRepository authDomainRepository;

    public AuthService(IAuthDomainRepository authDomainRepository) {
        this.authDomainRepository = authDomainRepository;
    }

    @Override
    public User authenticate(String username, String password) {
        return authDomainRepository.authenticate(username, password);
    }
}
