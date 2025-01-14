package com.javierito.javierito_importer.infrastructure.mappers.User;

import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.infrastructure.entities.UserEntity;

public interface UserMapper {
    User toUser(UserEntity target);
    UserEntity toUserEntity(User target);
}
