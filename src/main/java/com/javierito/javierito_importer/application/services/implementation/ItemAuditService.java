package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.interfaces.IItemAuditService;
import com.javierito.javierito_importer.domain.models.ItemAudit;
import com.javierito.javierito_importer.domain.ports.IItemAuditDomainRepository;

import java.util.ArrayList;

public class ItemAuditService implements IItemAuditService {

    private final IItemAuditDomainRepository itemAuditDomainRepository;

    public ItemAuditService(IItemAuditDomainRepository itemAuditDomainRepository) {this.itemAuditDomainRepository = itemAuditDomainRepository;}

    @Override
    public ArrayList<ItemAudit> recycleBin() {
        return itemAuditDomainRepository.recycleBin();
    }
}
