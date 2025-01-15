package com.javierito.javierito_importer.infrastructure.conf;

import com.javierito.javierito_importer.application.services.implementation.AuthService;
import com.javierito.javierito_importer.application.services.implementation.ItemService;
import com.javierito.javierito_importer.application.services.implementation.UserService;
import com.javierito.javierito_importer.application.services.interfaces.IAuthService;
import com.javierito.javierito_importer.application.services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.application.services.interfaces.IUserService;
import com.javierito.javierito_importer.domain.ports.*;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IAuthRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IUserRepository;
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
                                 IClientDomainRepository clientDomainRepository){
        return new UserService(userRepository, employeeDomainRepository, clientDomainRepository);
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
}
