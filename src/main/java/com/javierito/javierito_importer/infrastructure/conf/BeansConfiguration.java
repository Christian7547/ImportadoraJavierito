package com.javierito.javierito_importer.infrastructure.conf;

import com.javierito.javierito_importer.application.services.implementation.ItemService;
import com.javierito.javierito_importer.application.services.implementation.UserService;
import com.javierito.javierito_importer.application.services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.application.services.interfaces.IUserService;
import com.javierito.javierito_importer.domain.ports.IClientDomainRepository;
import com.javierito.javierito_importer.domain.ports.IEmployeeDomainRepository;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;
import com.javierito.javierito_importer.domain.ports.IUserDomainRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    IItemSerivce itemBeanService(IItemDomainRepository iItemDomainRepository){
        return new ItemService(iItemDomainRepository);
    }

    @Bean
    IUserService userBeanService(IUserDomainRepository userRepository,
                                 IEmployeeDomainRepository employeeDomainRepository,
                                 IClientDomainRepository clientDomainRepository){
        return new UserService(userRepository, employeeDomainRepository, clientDomainRepository);
    }
}
