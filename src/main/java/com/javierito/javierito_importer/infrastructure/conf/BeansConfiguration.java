package com.javierito.javierito_importer.infrastructure.conf;

import com.javierito.javierito_importer.application.Services.implementation.*;
import com.javierito.javierito_importer.application.Services.interfaces.*;
import com.javierito.javierito_importer.domain.ports.*;
import com.javierito.javierito_importer.domain.ports.output.IEmailServer;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class BeansConfiguration {

    private final IAuthRepository authRepository;

    @Bean
    IItemSerivce itemBeanService(IItemDomainRepository iItemDomainRepository){
        return new ItemService(iItemDomainRepository);
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

    @Bean
    IBrandService brandService(IBrandDomainRepository brandDomainRepository){
        return new BrandService(brandDomainRepository);
    }

    @Bean
    ISubCategoryService subCategoryService(ISubCategoryDomainRepository subCategoryDomainRepository){
        return new SubCategoryService(subCategoryDomainRepository);
    }

    @Bean
    ItemAddressService itemAddressService(IItemAddressDomainRepository itemAddressDomainRepository){
        return new ItemAddressService(itemAddressDomainRepository);
    }
}
