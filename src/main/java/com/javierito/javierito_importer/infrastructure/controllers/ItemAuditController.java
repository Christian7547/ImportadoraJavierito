package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.services.implementation.ItemAuditService;
import com.javierito.javierito_importer.application.services.interfaces.IItemAuditService;
import com.javierito.javierito_importer.domain.models.ItemAudit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/itemAudits")
public class ItemAuditController {

    private final IItemAuditService itemAuditService;

    public ItemAuditController(IItemAuditService itemAuditService) {this.itemAuditService = itemAuditService;}

    @GetMapping("/recycleBin")
    public ResponseEntity<ArrayList<ItemAudit>> getItemAudits() {
        return new ResponseEntity<>(itemAuditService.recycleBin(), HttpStatus.OK);
    }


}
