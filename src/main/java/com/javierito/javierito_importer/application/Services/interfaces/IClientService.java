package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.ClientModels.ListClients;
import com.javierito.javierito_importer.domain.models.ClientModels.NewClient;

import java.util.List;

public interface IClientService {

    boolean insertClient(NewClient client);
    List<ListClients> getClient(String ci);

}
