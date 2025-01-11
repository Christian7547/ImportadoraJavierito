package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.Client;
import com.javierito.javierito_importer.infrastructure.entities.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toClient(ClientEntity target);
    ClientEntity toClientEntity(Client target);
}
