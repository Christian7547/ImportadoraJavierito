package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IUserService;
import com.javierito.javierito_importer.application.Utils.*;
import com.javierito.javierito_importer.domain.models.EmployeeModels.Employee;
import com.javierito.javierito_importer.domain.models.userModels.User;
import com.javierito.javierito_importer.domain.models.userModels.UserList;
import com.javierito.javierito_importer.domain.ports.IEmployeeDomainRepository;
import com.javierito.javierito_importer.domain.ports.IUserDomainRepository;
import com.javierito.javierito_importer.domain.ports.output.IEmailServer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserDomainRepository userDomainRepository;
    private final IEmployeeDomainRepository employeeDomainRepository;
    private final IEmailServer emailServer;

    @Override
    public List<UserList> getAll(int limit, int offset,
                                 @Nullable Short status,
                                 @Nullable String role,
                                 @Nullable Integer officeId,
                                 @Nullable String someName) {
        Pageable pageable = PageRequest.of(offset, limit);
        return userDomainRepository.getAll(pageable, status, role, officeId, someName);
    }

    @Transactional
    @Override
    public User createUser(User user, Employee employee) {
        String passwordGenerated = getNewPassword();
        user.setPassword(getHasher(passwordGenerated));
        user.setUserName(getNewUserName(employee.getName(), employee.getLastName(), employee.getSecondLastName()));
        var userCreated = userDomainRepository.saveUser(user);
        long userId = userCreated.getId();
        employee.setUserId(userId);
        var employeeCreated = employeeDomainRepository.saveEmployee(employee);
        if(employeeCreated != null){
            emailServer.sendCredentials(user.getEmail(), employee.getName(), employee.getLastName(), user.getUserName(), passwordGenerated);
        }
        return userCreated;
    }

    @Transactional
    @Override
    public boolean updateUser(User user, Employee employee) {
        User getUser = getById(user.getId());
        Employee getEmployee = employeeDomainRepository.getByUserId(employee.getUserId());
        getUser.setEmail(user.getEmail());
        getEmployee.setName(employee.getName());
        getEmployee.setLastName(employee.getLastName());
        getEmployee.setSecondLastName(employee.getSecondLastName());
        getEmployee.setCi(employee.getCi());
        getEmployee.setPhoneNumber(employee.getPhoneNumber());
        User userRes = userDomainRepository.saveUser(getUser);
        Employee employeeRes = employeeDomainRepository.saveEmployee(getEmployee);
        return userRes != null && employeeRes != null;
    }

    @Override
    public long changeStatus(long id, short newStatus) {
        var findUser = getById(id);
        if(findUser != null){
            findUser.setStatus(newStatus);
            return userDomainRepository.saveUser(findUser).getId();
        }
        return 0;
    }

    @Override
    public Pair<User, String> getByEmail(String email) {
        User user = userDomainRepository.getByEmail(email);
        if(user != null){
            String code = Generator.generateRecoveryCode();
            emailServer.sendEmail(user.getEmail(), "Código de recuperación: " + code);
            return Pair.of(user, code);
        }
        return null;
    }

    @Override
    public User getById(long id) {
        User res = userDomainRepository.getById(id);
        return res;
    }

    @Override
    public long countUsers() {
        return userDomainRepository.countUsers();
    }

    public long countActiveUsers() {
        return userDomainRepository.countActiveUsers();
    }

    public long countInactiveUsers() {
        return userDomainRepository.countInactiveUsers();
    }

    private String getNewUserName(String name, String lastName, String secondLastName){
        return Generator.generateUsername(name, lastName, secondLastName);
    }

    private String getNewPassword(){
        return Generator.generatePassword();
    }

    private String getHasher(String text){
        return new BCryptPasswordEncoder(16).encode(text);
    }
}
