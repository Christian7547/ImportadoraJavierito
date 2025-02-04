package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IUserService;
import com.javierito.javierito_importer.application.Utils.*;
import com.javierito.javierito_importer.domain.models.Employee;
import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.domain.ports.IEmployeeDomainRepository;
import com.javierito.javierito_importer.domain.ports.IUserDomainRepository;
import com.javierito.javierito_importer.domain.ports.output.IEmailServer;
import org.springframework.beans.factory.annotation.Value;

public class UserService implements IUserService {

    private final IUserDomainRepository userDomainRepository;
    private final IEmployeeDomainRepository employeeDomainRepository;
    private final IEmailServer emailServer;

    @Value("${spring.mail.username}")
    private String email;

    public UserService(IUserDomainRepository userDomainRepository,
                       IEmployeeDomainRepository employeeDomainRepository,
                       IEmailServer emailServer) {
        this.userDomainRepository = userDomainRepository;
        this.employeeDomainRepository = employeeDomainRepository;
        this.emailServer = emailServer;
    }

    @Override
    public User createEmployeeUser(User user, Employee employee) {
        var userCreated = userDomainRepository.createUser(user);
        long userId = userCreated.getId();
        employee.setUserId(userId);
        var employeeCreated = employeeDomainRepository.createEmployee(employee);
        if(userCreated != null){
            emailServer.sendEmail(user.getEmail(), email, "<h1>Hola desde spring<h1/>");
        }
        return userCreated;
    }

    @Override
    public User getByEmail(String email) {
        User user = userDomainRepository.getByEmail(email);
        if(user != null){
            String code = Generator.GenerateRecoveryCode();
            emailServer.sendEmail(user.getEmail(), this.email, "Código de recuperación: " + code);
            return user;
        }
        return null;
    }

    @Override
    public void removeUser(User user) {
        user.setStatus((short)0);
        userDomainRepository.removeUser(user);
    }
}
