package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.domain.ports.IUserDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IUserRepository;
import com.javierito.javierito_importer.infrastructure.entities.UserEntity;
import com.javierito.javierito_importer.infrastructure.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserDomainRepository {

    @Autowired
    private UserMapper userMapper;

    private final IUserRepository userRepository;

    public UserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll(Pageable pageable) {
        var entities = userRepository.findAll(pageable);
        return userMapper.toUsersList(entities.toList());
    }

    @Override
    public User createUser(User user) {
        var toEntity = userMapper.toUserEntity(user);
        var hash = new BCryptPasswordEncoder().encode(toEntity.getPassword());
        toEntity.setPassword(hash);
        var userCreated = userRepository.save(toEntity);
        return userMapper.toUser(userCreated);
    }

    @Override
    public User getByEmail(String email) {
        Optional<UserEntity> entity = userRepository.findByEmail(email);
        if(entity.isPresent()){
            User model = userMapper.toUser(entity.get());
            return model;
        }
        return null;
    }

    @Override
    public User getById(long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        return userMapper.toUser(entity.get());
    }

    @Override
    public void removeUser(User user) {

    }
}
