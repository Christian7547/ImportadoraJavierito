package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.interfaces.IUserService;
import com.javierito.javierito_importer.domain.models.Client;
import com.javierito.javierito_importer.domain.models.Employee;
import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.domain.ports.IClientDomainRepository;
import com.javierito.javierito_importer.domain.ports.IEmployeeDomainRepository;
import com.javierito.javierito_importer.domain.ports.IUserDomainRepository;

public class UserService implements IUserService {

    private final IUserDomainRepository userDomainRepository;
    private final IEmployeeDomainRepository employeeDomainRepository;
    private final IClientDomainRepository clientDomainRepository;

    public UserService(IUserDomainRepository userDomainRepository,
                       IEmployeeDomainRepository employeeDomainRepository,
                       IClientDomainRepository clientDomainRepository) {
        this.userDomainRepository = userDomainRepository;
        this.employeeDomainRepository = employeeDomainRepository;
        this.clientDomainRepository = clientDomainRepository;
    }

    @Override
    public User createEmployeeUser(User user, Employee employee) {
        var userCreated = userDomainRepository.createUser(user);
        long userId = userCreated.getId();
        employee.setUserId(userId);
        var employeeCreated = employeeDomainRepository.createEmployee(employee);
        return userCreated;
    }

    @Override
    public void removeUser(User user) {
        user.setStatus((short)0);
        userDomainRepository.removeUser(user);
    }
}
