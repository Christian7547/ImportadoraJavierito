package com.javierito.javierito_importer.application.services.interfaces;

import com.javierito.javierito_importer.domain.models.ItemAudit;
import com.javierito.javierito_importer.infrastructure.dtos.ExcelReportsDTO;

import java.util.ArrayList;

public interface IItemAuditService {

    ArrayList<ItemAudit> recycleBin();

    ArrayList<ItemAudit> excelReports(ExcelReportsDTO excelReportsDTO);
}
