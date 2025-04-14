package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.userModels.User;
import com.javierito.javierito_importer.domain.ports.IAuthDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IAuthRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IUserRepository;
import com.javierito.javierito_importer.infrastructure.entities.UserEntity;
import com.javierito.javierito_importer.infrastructure.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


@Repository
public class AuthRepository implements IAuthDomainRepository {

    @Autowired
    private UserMapper userMapper;

    private final IAuthRepository authRepository;
    private final IUserRepository userRepository;

    public AuthRepository(IAuthRepository authRepository, IUserRepository userRepository) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User authenticate(String username, String password) {
        var getUser = authRepository.findByUserName(username);
        if(getUser.isPresent())
        {
            var checkIfPassword = new BCryptPasswordEncoder(16).matches(password, getUser.get().getPassword());
            if(checkIfPassword)
                return userMapper.toUser(getUser.get());
            return null;
        }
        return null;
    }

    @Override
    public User resetPassword(User userWithNewPassword) {
        UserEntity toEntity = userMapper.toUserEntity(userWithNewPassword);
        User result = userMapper.toUser(userRepository.save(toEntity));
        return result;
    }
}
