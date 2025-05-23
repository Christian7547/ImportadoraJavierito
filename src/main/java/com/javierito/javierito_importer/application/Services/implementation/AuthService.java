package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IAuthService;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.domain.models.EmployeeModels.Employee;
import com.javierito.javierito_importer.domain.models.userModels.User;
import com.javierito.javierito_importer.domain.ports.IAuthDomainRepository;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeDomainRepository;
import com.javierito.javierito_importer.domain.ports.IEmployeeDomainRepository;
import com.javierito.javierito_importer.domain.ports.IUserDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IAuthDomainRepository authDomainRepository;
    private final IUserDomainRepository userDomainRepository;
    private final IEmployeeDomainRepository employeeDomainRepository;
    private final IBranchOfficeDomainRepository branchOfficeDomainRepository;

    @Override
    public Pair<User, String> authenticate(String username, String password) {
        User user = authDomainRepository.authenticate(username, password);
        if(user == null) {
            return null;
        }
        Employee employee = employeeDomainRepository.getByUserId(user.getId());
        BranchOffice branchOffice = branchOfficeDomainRepository.getById(employee.getBranchOfficeId());
        return Pair.of(user, branchOffice.getName());
    }

    @Override
    public User resetPassword(String email, String newPassword) {
        User getUser = userDomainRepository.getByEmail(email);
        if(getUser != null){
            String hash = new BCryptPasswordEncoder(16).encode(newPassword);
            getUser.setPassword(hash);
            getUser.setLastUpdate(LocalDateTime.now());
            return authDomainRepository.resetPassword(getUser);
        }
        return null;
    }

    @Override
    public User changeFirstLogin(long userId) {
        User getUser = userDomainRepository.getById(userId);
        if(getUser == null) {
            return null;
        }
        getUser.setFirstLogin("1");
        return userDomainRepository.saveUser(getUser);
    }
}
