package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IAuthService;
import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.domain.ports.IAuthDomainRepository;
import com.javierito.javierito_importer.domain.ports.IUserDomainRepository;

public class AuthService implements IAuthService {

    private final IAuthDomainRepository authDomainRepository;
    private final IUserDomainRepository userDomainRepository;

    public AuthService(IAuthDomainRepository authDomainRepository, IUserDomainRepository userDomainRepository) {
        this.authDomainRepository = authDomainRepository;
        this.userDomainRepository = userDomainRepository;
    }


    @Override
    public User authenticate(String username, String password) {
        return authDomainRepository.authenticate(username, password);
    }

    @Override
    public User resetPassword(String email, String newPassword) {
        User getUser = userDomainRepository.getByEmail(email);
        if(getUser != null){
            getUser.setPassword(newPassword);
            return authDomainRepository.resetPassword(getUser);
        }
        return null;
    }
}
