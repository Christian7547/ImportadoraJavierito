package com.javierito.javierito_importer.infrastructure.adapters.implementation;


import com.javierito.javierito_importer.domain.models.ItemAddress;
import com.javierito.javierito_importer.domain.ports.IItemAddressDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IBranRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IItemAddressRepository;
import com.javierito.javierito_importer.infrastructure.mappers.ItemAddressMapper;
import com.javierito.javierito_importer.infrastructure.mappers.ItemAuditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ItemAddressRepository implements IItemAddressDomainRepository {

    @Autowired
    private ItemAddressMapper itemAddressMapper;

    private final IItemAddressRepository itemAddressRepository;

    public ItemAddressRepository(IItemAddressRepository itemAddressRepository) {this.itemAddressRepository = itemAddressRepository;}


    @Override
    public ArrayList<ItemAddress> getItemAddresses() {
        return this.itemAddressMapper.toItemAdreses(this.itemAddressRepository.findAll());
    }
}
