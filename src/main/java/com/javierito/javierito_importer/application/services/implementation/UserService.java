package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.interfaces.IUserService;
import com.javierito.javierito_importer.domain.models.Client;
import com.javierito.javierito_importer.domain.models.Employee;
import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.domain.ports.IClientDomainRepository;
import com.javierito.javierito_importer.domain.ports.IEmployeeDomainRepository;
import com.javierito.javierito_importer.domain.ports.IUserDomainRepository;
import com.javierito.javierito_importer.domain.ports.output.IEmailServer;
import org.springframework.beans.factory.annotation.Value;

public class UserService implements IUserService {

    private final IUserDomainRepository userDomainRepository;
    private final IEmployeeDomainRepository employeeDomainRepository;
    private final IClientDomainRepository clientDomainRepository;
    private final IEmailServer emailServer;

    @Value("${spring.mail.username}")
    private String email;

    public UserService(IUserDomainRepository userDomainRepository,
                       IEmployeeDomainRepository employeeDomainRepository,
                       IClientDomainRepository clientDomainRepository,
                       IEmailServer emailServer) {
        this.userDomainRepository = userDomainRepository;
        this.employeeDomainRepository = employeeDomainRepository;
        this.clientDomainRepository = clientDomainRepository;
        this.emailServer = emailServer;
    }

    @Override
    public User createEmployeeUser(User user, Employee employee) {
        var userCreated = userDomainRepository.createUser(user);
        long userId = userCreated.getId();
        employee.setUserId(userId);
        var employeeCreated = employeeDomainRepository.createEmployee(employee);
        if(userCreated != null){
            emailServer.sendEmail(user.getEmail(), email, "<h1>Hola desde spring</h1>");
            return userCreated;
        }
        return null;
    }

    @Override
    public User createClientUser(User user, Client client) {
        var userCreated = userDomainRepository.createUser(user);
        long userId = userCreated.getId();
        client.setUserId(userId);
        var userC = clientDomainRepository.createClient(client);

        return userCreated;
    }

    @Override
    public void removeUser(User user) {
        user.setStatus((short)0);
        userDomainRepository.removeUser(user);
    }
}
