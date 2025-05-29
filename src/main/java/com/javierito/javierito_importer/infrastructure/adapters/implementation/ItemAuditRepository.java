package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.ItemAudit;
import com.javierito.javierito_importer.domain.ports.IItemAuditDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IItemAuditRepository;
import com.javierito.javierito_importer.infrastructure.dtos.ItemAudit.ExcelReportsDTO;
import com.javierito.javierito_importer.infrastructure.mappers.ItemAuditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Repository
public class ItemAuditRepository implements IItemAuditDomainRepository {
    @Autowired
    private ItemAuditMapper itemAuditMapper;

    private final IItemAuditRepository itemAuditRepository;

    public ItemAuditRepository(IItemAuditRepository itemAuditRepository) {this.itemAuditRepository = itemAuditRepository;}

    @Override
    public ArrayList<ItemAudit> excelReports(ExcelReportsDTO excelReportsDTO) {
        System.out.println(excelReportsDTO);
        return this.itemAuditMapper.toItemAudits(this.itemAuditRepository.findAll())
                .stream()
                .filter(itemAudit -> {

                    if (excelReportsDTO.getStartDate() != null && excelReportsDTO.getEndDate() != null) {
                        return !itemAudit.getActionDate().isBefore(excelReportsDTO.getStartDate()) &&
                                !itemAudit.getActionDate().isAfter(excelReportsDTO.getEndDate());
                    }
                    return true;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

