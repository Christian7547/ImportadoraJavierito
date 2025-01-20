package com.javierito.javierito_importer.infrastructure.mappers.User;

import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.infrastructure.entities.ItemEntity;
import com.javierito.javierito_importer.infrastructure.entities.UserEntity;
import org.mapstruct.MappingTarget;

public interface UserMapper {
    User toUser(UserEntity target);
    UserEntity toUserEntity(User target);
}
