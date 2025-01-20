package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.services.interfaces.IBranchOfficeService;
import com.javierito.javierito_importer.domain.models.BranchOffice;
import com.javierito.javierito_importer.infrastructure.dtos.NewBranchOfficeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/branchOffice")
@RequiredArgsConstructor
public class BranchOfficeController {

    private final IBranchOfficeService branchOfficeService;

    @RequestMapping("/createBranchOffice")
    public ResponseEntity<?> createBranchOfficeAsync(@RequestBody NewBranchOfficeDTO newBranchOfficeDTO) {
        BranchOffice branchOffice = BranchOffice.builder()
                .name(newBranchOfficeDTO.getName())
                .address(newBranchOfficeDTO.getAddress())
                .latitude(newBranchOfficeDTO.getLatitude())
                .longitude(newBranchOfficeDTO.getLongitude())
                .build();
        var created = branchOfficeService.createBranchOffice(branchOffice, newBranchOfficeDTO.getPathImages());
        if(created != null){
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Could not save changes", HttpStatus.BAD_REQUEST);
    }
}
