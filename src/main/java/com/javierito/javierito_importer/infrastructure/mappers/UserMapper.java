package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userName_", target = "userName")
    User toUser(UserEntity target);

    @Mapping(source = "userName", target = "userName_")
    UserEntity toUserEntity(User target);
}
