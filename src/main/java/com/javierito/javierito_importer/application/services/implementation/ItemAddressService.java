package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.interfaces.IItemAddressService;
import com.javierito.javierito_importer.domain.models.ItemAddress;
import com.javierito.javierito_importer.domain.ports.IItemAddressDomainRepository;

import java.util.ArrayList;

public class ItemAddressService implements IItemAddressService {

    private final IItemAddressDomainRepository itemAddressDomainRepository;
    public ItemAddressService(IItemAddressDomainRepository itemAddressDomainRepository) { this.itemAddressDomainRepository = itemAddressDomainRepository; }

    @Override
    public ArrayList<ItemAddress> getAllItemAddresses() {
        return itemAddressDomainRepository.getItemAddresses();
    }
}
