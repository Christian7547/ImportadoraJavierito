package com.javierito.javierito_importer.infrastructure.mappers.User;

import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public User toUser(UserEntity target) {
        if (target == null) {
            return null;
        }

        return User.builder()
                .id(target.getId())
                .userName(target.getUsername())
                .password(target.getPassword())
                .role(target.getRole())
                .email(target.getEmail())
                .status(target.getStatus())
                .registerDate(target.getRegisterDate())
                .lastUpdate(target.getLastUpdate())
                .build();
    }

    @Override
    public UserEntity toUserEntity(User target) {
        if (target == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(target.getId());
        userEntity.setUserName(target.getUserName());
        userEntity.setPassword(target.getPassword());
        userEntity.setRole(target.getRole());
        userEntity.setEmail(target.getEmail());
        userEntity.setStatus(target.getStatus());
        userEntity.setRegisterDate(target.getRegisterDate());
        userEntity.setLastUpdate(target.getLastUpdate());

        return userEntity;
    }
}
