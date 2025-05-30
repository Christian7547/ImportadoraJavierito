package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IItemAuditService;
import com.javierito.javierito_importer.domain.models.ItemAudit;
import com.javierito.javierito_importer.domain.ports.IItemAuditDomainRepository;
import com.javierito.javierito_importer.infrastructure.dtos.ItemAudit.ExcelReportsDTO;

import java.util.ArrayList;

public class ItemAuditService implements IItemAuditService {

    private final IItemAuditDomainRepository itemAuditDomainRepository;

    public ItemAuditService(IItemAuditDomainRepository itemAuditDomainRepository) {this.itemAuditDomainRepository = itemAuditDomainRepository;}


    @Override
    public ArrayList<ItemAudit> excelReports(ExcelReportsDTO excelReportsDTO) {

        if (excelReportsDTO.getStartDate() != null) {
            excelReportsDTO.getStartDate().minusDays(1);
        } else if (excelReportsDTO.getEndDate() != null) {
            excelReportsDTO.getEndDate().plusDays(1);
        }

        return itemAuditDomainRepository.excelReports(excelReportsDTO);
    }

}
