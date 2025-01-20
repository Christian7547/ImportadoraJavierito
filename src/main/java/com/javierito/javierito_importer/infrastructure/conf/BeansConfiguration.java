package com.javierito.javierito_importer.infrastructure.conf;

import com.javierito.javierito_importer.application.services.implementation.*;
import com.javierito.javierito_importer.application.services.interfaces.*;
import com.javierito.javierito_importer.domain.ports.*;
import com.javierito.javierito_importer.domain.ports.output.IEmailServer;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfiguration {

    private final IAuthRepository authRepository;

    @Bean
    IItemSerivce itemBeanService(IItemDomainRepository iItemDomainRepository,
                                 IItemImageDomainRepository iItemImageDomainRepository,
                                 IStockDomainRepository stockDomainRepository){
        return new ItemService(iItemDomainRepository, iItemImageDomainRepository, stockDomainRepository);
    }

    @Bean
    IUserService userBeanService(IUserDomainRepository userRepository,
                                 IEmployeeDomainRepository employeeDomainRepository,
                                 IEmailServer emailServer){
        return new UserService(userRepository, employeeDomainRepository, emailServer);
    }

    @Bean
    IAuthService authBeanService(IAuthDomainRepository authDomainRepository){
        return new AuthService(authDomainRepository);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> authRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    IItemAuditService itemAuditService(IItemAuditDomainRepository itemAuditDomainRepository){
        return new ItemAuditService(itemAuditDomainRepository);
    }

    @Bean
    IBranchOfficeService branchOfficeService(IBranchOfficeDomainRepository branchOfficeDomainRepository,
                                             IBranchOfficeImageDomainRepository branchOfficeImageDomainRepository){
        return new BranchOfficeService(branchOfficeDomainRepository, branchOfficeImageDomainRepository);
    }

    @Bean
    IBranchOfficeImageService branchOfficeImageService(IBranchOfficeImageDomainRepository branchOfficeImageDomainRepository){
        return new BranchOfficeImageService(branchOfficeImageDomainRepository);
    }
}
