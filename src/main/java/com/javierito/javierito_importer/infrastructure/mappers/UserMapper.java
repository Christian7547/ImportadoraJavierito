package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.userModels.User;
import com.javierito.javierito_importer.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userName_", target = "userName")
    @Mapping(source = "firstLogin", target = "firstLogin")
    User toUser(UserEntity target);

    @Mapping(source = "userName", target = "userName_")
    @Mapping(source = "firstLogin", target = "firstLogin")
    UserEntity toUserEntity(User target);

    List<UserEntity> toUserEntityList(List<User> target);

    List<User> toUsersList(List<UserEntity> target);
}
