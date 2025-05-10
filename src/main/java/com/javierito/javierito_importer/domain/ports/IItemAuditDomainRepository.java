package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.ItemAudit;
import com.javierito.javierito_importer.infrastructure.dtos.ItemAudit.ExcelReportsDTO;

import java.util.ArrayList;

public interface IItemAuditDomainRepository {

    ArrayList<ItemAudit> excelReports (ExcelReportsDTO excelReportsDTO);

}
