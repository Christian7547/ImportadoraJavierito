package com.javierito.javierito_importer.infrastructure.controllers;


import com.javierito.javierito_importer.application.Services.interfaces.IReportService;
import com.javierito.javierito_importer.domain.models.InsertReport;
import com.javierito.javierito_importer.domain.models.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/insertReport")
    public ResponseEntity<?> insertReport(@RequestBody InsertReport report) {

        var result = reportService.insertReport(report);
        
        if (result != 0)

            return new ResponseEntity<>(result, HttpStatus.OK);

        return new ResponseEntity<>("Could not insert report", HttpStatus.BAD_REQUEST);
    }
}
