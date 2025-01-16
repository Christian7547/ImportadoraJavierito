package com.javierito.javierito_importer.infrastructure.conf;

import com.javierito.javierito_importer.application.services.implementation.AuthService;
import com.javierito.javierito_importer.application.services.implementation.ItemAuditService;
import com.javierito.javierito_importer.application.services.implementation.ItemService;
import com.javierito.javierito_importer.application.services.implementation.UserService;
import com.javierito.javierito_importer.application.services.interfaces.IAuthService;
import com.javierito.javierito_importer.application.services.interfaces.IItemAuditService;
import com.javierito.javierito_importer.application.services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.application.services.interfaces.IUserService;
import com.javierito.javierito_importer.domain.ports.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    IItemSerivce itemBeanService(IItemDomainRepository iItemDomainRepository,
                                 IItemImageDomainRepository iItemImageDomainRepository,
                                 IStockDomainRepository stockDomainRepository){
        return new ItemService(iItemDomainRepository, iItemImageDomainRepository, stockDomainRepository);
    }

    @Bean
    IUserService userBeanService(IUserDomainRepository userRepository,
                                 IEmployeeDomainRepository employeeDomainRepository,
                                 IClientDomainRepository clientDomainRepository){
        return new UserService(userRepository, employeeDomainRepository, clientDomainRepository);
    }

    @Bean
    IAuthService authBeanService(IAuthDomainRepository authDomainRepository){
        return new AuthService(authDomainRepository);
    }

    @Bean
    IItemAuditService itemAuditService(IItemAuditDomainRepository itemAuditDomainRepository){
        return new ItemAuditService(itemAuditDomainRepository);
    }
}
