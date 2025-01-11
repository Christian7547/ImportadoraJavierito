package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.Client;

public interface IClientDomainRepository {
    Client createClient(Client client);
    void removeClient(Client client);
}
