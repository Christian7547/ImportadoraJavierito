package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.domain.ports.IAuthDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IAuthRepository;
import com.javierito.javierito_importer.infrastructure.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository implements IAuthDomainRepository {

    @Autowired
    private UserMapper userMapper;

    private final IAuthRepository authRepository;

    public AuthRepository(IAuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public User authenticate(String username, String password) {
        var getUser = authRepository.findByUserName(username);
        if(getUser != null)
        {
            var checkIfPassword = new BCryptPasswordEncoder().matches(password, getUser.getPassword());
            if(checkIfPassword)
                return userMapper.toUser(getUser);
            return null;
        }
        return null;
    }
}
