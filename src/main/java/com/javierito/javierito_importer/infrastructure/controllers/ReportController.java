package com.javierito.javierito_importer.infrastructure.controllers;


import com.javierito.javierito_importer.application.Services.interfaces.IReportService;
import com.javierito.javierito_importer.domain.models.BarcodeModels.exception.ReportNotFoundException;
import com.javierito.javierito_importer.domain.models.InsertReport;
import com.javierito.javierito_importer.domain.models.Report;
import com.javierito.javierito_importer.domain.models.userModels.UserList;
import lombok.RequiredArgsConstructor;
import org.javatuples.Quartet;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate,
                                        String order) {

        var reports = reportService.getReports(limit, offset, param, startDate, endDate, order);
        long totalReports = reportService.countAll();
        long totalReportSales = reportService.countAllSales();
        long totalReportsInventory = reportService.countAllInventory();
        Quartet<List<Report>, Long, Long, Long> data = Quartet.with(reports, totalReports, totalReportSales, totalReportsInventory);
        if (data  == null) {
            return new ResponseEntity<>("Could not get item", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/getReportSales")
    public ResponseEntity<?> getReportSales(
                                            @RequestParam(defaultValue = "5") int limit,
                                            @RequestParam(defaultValue = "1") int offset,
                                            String param,
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate,
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)String order) {
        var reports = reportService.getAllReportSales(limit, offset, param, startDate, endDate, order);
        long totalReports = reportService.countAll();
        long totalReportSales = reportService.countAllSales();
        long totalReportsInventory = reportService.countAllInventory();
        Quartet<List<Report>, Long, Long, Long> data = Quartet.with(reports, totalReports, totalReportSales, totalReportsInventory);
        if (data  == null) {
            return new ResponseEntity<>("Could not get item", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/getReportInventory")
    public ResponseEntity<?> getReportInventory(
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "1") int offset,
            String param,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate,
            String order) {
        var reports = reportService.getAllReporInventories(limit, offset, param, startDate, endDate, order);
        long totalReports = reportService.countAll();
        long totalReportSales = reportService.countAllSales();
        long totalReportsInventory = reportService.countAllInventory();
        Quartet<List<Report>, Long, Long, Long> data = Quartet.with(reports, totalReports, totalReportSales, totalReportsInventory);
        if (data  == null) {
            return new ResponseEntity<>("Could not get item", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/insertReport")
    public ResponseEntity<?> insertReport(@RequestBody InsertReport report) {

        var result = reportService.insertReport(report);
        
        if (result != 0)

            return new ResponseEntity<>(result, HttpStatus.OK);

        return new ResponseEntity<>("Could not insert report", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteReport")
    public ResponseEntity<?> deleteReport(@RequestBody Long reportId) {
        try {
            reportService.deleteReport(reportId);
            return new ResponseEntity<>("Report deleted", HttpStatus.OK);
        }
        catch (ReportNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
