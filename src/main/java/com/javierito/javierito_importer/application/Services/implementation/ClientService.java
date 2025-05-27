package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IClientService;
import com.javierito.javierito_importer.domain.models.ClientModels.ListClients;
import com.javierito.javierito_importer.domain.models.ClientModels.NewClient;
import com.javierito.javierito_importer.domain.ports.IClientDomainRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final IClientDomainRepository clientDomainRepository;

    @Override
    public boolean insertClient(NewClient client) {
        return clientDomainRepository.insertClient(client);
    }

    @Override
    public List<ListClients> getClient(String ci) {
        return clientDomainRepository.getClient(ci);
    }
}
