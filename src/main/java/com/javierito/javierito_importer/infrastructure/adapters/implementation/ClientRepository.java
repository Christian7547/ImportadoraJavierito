package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.Client;
import com.javierito.javierito_importer.domain.models.ClientModels.ListClients;
import com.javierito.javierito_importer.domain.models.ClientModels.NewClient;
import com.javierito.javierito_importer.domain.ports.IClientDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IClientRepository;
import com.javierito.javierito_importer.infrastructure.mappers.ClientMapper;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository implements IClientDomainRepository {

    @PersistenceContext
    private EntityManager entityManager;

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

    @Override
    public boolean insertClient(NewClient client) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("usp_insert_client");

        query.registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_lastName", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_secondLastName", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_ci", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_phoneNumber", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_user_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_success", Boolean.class, ParameterMode.OUT);

        query.setParameter("p_name", client.getName());
        query.setParameter("p_lastName", client.getLastName());
        query.setParameter("p_secondLastName", client.getSecondLastName());
        query.setParameter("p_ci", client.getCi());
        query.setParameter("p_phoneNumber", client.getPhoneNumber());
        query.setParameter("p_user_id", client.getUserId());

        query.execute();
        
        Boolean result = (Boolean) query.getOutputParameterValue("p_success");
        return result != null ? result : false;
    }

    @Override
    public List<ListClients> getClient(String ci) {

        String sql = "SELECT * FROM ufc_get_client_by_ci(:p_ci)";

        Query query = entityManager.createNativeQuery(sql, ListClients.class);
        query.setParameter("p_ci", ci);

        List<ListClients> results = query.getResultList();
        return results.isEmpty() ? new ArrayList<>() : results;
    }
}
