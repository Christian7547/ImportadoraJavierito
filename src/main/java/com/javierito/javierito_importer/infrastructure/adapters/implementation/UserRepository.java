package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.domain.ports.IUserDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IUserRepository;
import com.javierito.javierito_importer.infrastructure.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserDomainRepository {

    @Autowired
    private UserMapper userMapper;

    private final IUserRepository userRepository;

    public UserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        var toEntity = userMapper.toUserEntity(user);
        var userCreated = userRepository.save(toEntity);
        return userMapper.toUser(userCreated);
    }

    @Override
    public void removeUser(User user) {

    }
}
