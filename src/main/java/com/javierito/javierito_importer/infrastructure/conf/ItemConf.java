package com.javierito.javierito_importer.infrastructure.conf;

import com.javierito.javierito_importer.application.services.implementation.ItemService;
import com.javierito.javierito_importer.application.services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemConf {
    @Bean
    IItemSerivce itemBeanService(IItemDomainRepository iItemDomainRepository){
        return new ItemService(iItemDomainRepository);
    }
}
