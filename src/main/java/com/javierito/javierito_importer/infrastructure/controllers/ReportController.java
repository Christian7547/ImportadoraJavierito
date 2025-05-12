package com.javierito.javierito_importer.infrastructure.controllers;


import com.javierito.javierito_importer.application.Services.interfaces.IReportService;
import com.javierito.javierito_importer.domain.models.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final IReportService reportService;

    @GetMapping("/getReports")
    public ResponseEntity<?> getReports(@RequestParam(defaultValue = "5") int limit,
                                        @RequestParam(defaultValue = "1") int offset,
                                        String param,
                                        Timestamp starDate,
                                        Timestamp endDate,
                                        String order) {

        var reports = reportService.getReports(limit, offset, param, starDate, endDate, order);

        if (reports != null)
            return new ResponseEntity<>(reports, HttpStatus.OK);
        return new ResponseEntity<>("Could not get item", HttpStatus.NOT_FOUND);
    }
}
