package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.ItemAudit;
import com.javierito.javierito_importer.infrastructure.dtos.ItemAudit.ExcelReportsDTO;

import java.util.ArrayList;

public interface IItemAuditService {

    ArrayList<ItemAudit> excelReports(ExcelReportsDTO excelReportsDTO);
}
