package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.services.interfaces.IBranchOfficeService;
import com.javierito.javierito_importer.domain.models.BranchOffice;
import com.javierito.javierito_importer.infrastructure.dtos.NewBranchOfficeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/branchOffices")
@RequiredArgsConstructor
public class BranchOfficeController {

    private final IBranchOfficeService branchOfficeService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getBranchOfficesAsync(){
        ArrayList<BranchOffice> offices = branchOfficeService.getAll();
        if(offices != null){
            return new ResponseEntity<>(offices, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not get branch offices", HttpStatus.OK);
    }

    @GetMapping("/getBranchOffice/{branchOfficeId}")
    public ResponseEntity<?> getBranchOfficeAsync(@PathVariable int branchOfficeId){
        var branchOffice = branchOfficeService.getById(branchOfficeId);
        if(branchOffice != null){
            return new ResponseEntity<>(branchOffice, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not get branch office", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/createBranchOffice")
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
