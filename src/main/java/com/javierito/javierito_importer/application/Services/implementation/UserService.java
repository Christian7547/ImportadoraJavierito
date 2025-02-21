package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IUserService;
import com.javierito.javierito_importer.application.Utils.*;
import com.javierito.javierito_importer.domain.models.Employee;
import com.javierito.javierito_importer.domain.models.User;
import com.javierito.javierito_importer.domain.ports.IEmployeeDomainRepository;
import com.javierito.javierito_importer.domain.ports.IUserDomainRepository;
import com.javierito.javierito_importer.domain.ports.output.IEmailServer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserDomainRepository userDomainRepository;
    private final IEmployeeDomainRepository employeeDomainRepository;
    private final IEmailServer emailServer;

    @Override
    public List<User> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userDomainRepository.getAll(pageable);
    }

    @Transactional
    @Override
    public User createUser(User user, Employee employee) {
        String userNameGenerated = Generator.generateUsername(employee.getName(), employee.getLastName(), employee.getSecondLastName());
        String passwordGenerated = Generator.generatePassword();
        user.setPassword(passwordGenerated);
        user.setUserName(userNameGenerated);
        var userCreated = userDomainRepository.createUser(user);
        long userId = userCreated.getId();
        employee.setUserId(userId);
        var employeeCreated = employeeDomainRepository.createEmployee(employee);
        if(employeeCreated != null){
            emailServer.sendCredentials(user.getEmail(), employee.getName(), employee.getLastName(), user.getUserName(), user.getPassword());
        }
        return userCreated;
    }

    @Override
    public User getByEmail(String email) {
        User user = userDomainRepository.getByEmail(email);
        if(user != null){
            String code = Generator.generateRecoveryCode();
            emailServer.sendEmail(user.getEmail(), "Código de recuperación: " + code);
            return user;
        }
        return null;
    }

    @Override
    public User getById(long id) {
        return userDomainRepository.getById(id);
    }

    @Override
    public void removeUser(User user) {
        user.setStatus((short)0);
        userDomainRepository.removeUser(user);
    }
}
