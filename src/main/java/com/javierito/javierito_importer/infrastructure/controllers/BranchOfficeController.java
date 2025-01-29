package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.services.interfaces.IBranchOfficeService;
import com.javierito.javierito_importer.domain.models.BranchOffice;
import com.javierito.javierito_importer.infrastructure.dtos.BranchOffice.NewBranchOfficeDTO;
import com.javierito.javierito_importer.infrastructure.mappers.BranchOfficeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/branchOffice")
@RequiredArgsConstructor
public class BranchOfficeController {

    @Autowired
    private BranchOfficeMapper branchOfficeMapper;

    private final IBranchOfficeService branchOfficeService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getBranchOffices(){
        ArrayList<BranchOffice> offices = branchOfficeService.getAll();
        if(offices != null){
            var data = branchOfficeMapper.toBranchOfficesDTO(offices);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not get branch offices", HttpStatus.OK);
    }

    @GetMapping("/getBranchOffice/{branchOfficeId}")
    public ResponseEntity<?> getBranchOffice(@PathVariable int branchOfficeId){
        var branchOffice = branchOfficeService.getById(branchOfficeId);
        if(branchOffice != null){
            return new ResponseEntity<>(branchOffice, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not get branch office", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/createBranchOffice")
    public ResponseEntity<?> createBranchOffice(@RequestBody NewBranchOfficeDTO newBranchOfficeDTO) {
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
