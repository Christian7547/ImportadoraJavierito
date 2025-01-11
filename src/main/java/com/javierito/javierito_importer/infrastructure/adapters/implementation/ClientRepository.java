package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.Client;
import com.javierito.javierito_importer.domain.ports.IClientDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IClientRepository;
import com.javierito.javierito_importer.infrastructure.mappers.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository implements IClientDomainRepository {

    @Autowired
    private ClientMapper clientMapper;

    private final IClientRepository clientRepository;

    public ClientRepository(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        var toEntity = clientMapper.toClientEntity(client);
        var clientCreated = clientRepository.save(toEntity);
        return clientMapper.toClient(clientCreated);
    }

    @Override
    public void removeClient(Client client) {

    }
}
