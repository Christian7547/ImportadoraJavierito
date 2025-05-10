package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IItemAuditService;
import com.javierito.javierito_importer.domain.models.ItemAudit;
import com.javierito.javierito_importer.infrastructure.dtos.ItemAudit.ExcelReportsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/itemAudits")
public class ItemAuditController {

    private final IItemAuditService itemAuditService;

    public ItemAuditController(IItemAuditService itemAuditService) {this.itemAuditService = itemAuditService;}


    @PostMapping("/excelReports")
    public ResponseEntity<?> getItemAuditsExcel(@RequestBody ExcelReportsDTO excelReportsDTO) {

        ArrayList<ItemAudit> excelReports = itemAuditService.excelReports(excelReportsDTO);

        if(excelReports != null){
            return new ResponseEntity<>(excelReports, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not get excel report", HttpStatus.OK);
    }
}
