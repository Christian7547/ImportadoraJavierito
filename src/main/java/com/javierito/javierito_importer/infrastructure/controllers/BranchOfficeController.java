package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IBranchOfficeImageService;
import com.javierito.javierito_importer.application.Services.interfaces.IBranchOfficeService;
import com.javierito.javierito_importer.domain.models.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeImage;
import com.javierito.javierito_importer.infrastructure.dtos.BranchOffice.BranchOfficeEditableDTO;
import com.javierito.javierito_importer.infrastructure.dtos.BranchOffice.NewBranchOfficeDTO;
import com.javierito.javierito_importer.infrastructure.dtos.BranchOffice.OfficeImageEditableDTO;
import com.javierito.javierito_importer.infrastructure.mappers.BranchOfficeImageMapper;
import com.javierito.javierito_importer.infrastructure.mappers.BranchOfficeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/branchOffice")
@RequiredArgsConstructor
public class BranchOfficeController {

    private final BranchOfficeMapper branchOfficeMapper;
    private final BranchOfficeImageMapper officeImageMapper;

    private final IBranchOfficeService branchOfficeService;
    private final IBranchOfficeImageService imageService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getBranchOffices(){
        ArrayList<BranchOffice> offices = branchOfficeService.getAll();
        if(offices != null){
            var data = branchOfficeMapper.toBranchOfficesDTO(offices);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not get branch offices", HttpStatus.OK);
    }

    @GetMapping("/getBranchOfficeDetails/{branchOfficeId}")
    public ResponseEntity<?> getBranchOffice(@PathVariable int branchOfficeId){
        BranchOffice branchOffice = branchOfficeService.getById(branchOfficeId);
        if(branchOffice != null){
            List<BranchOfficeImage> source = imageService.getImagesByBranchOfficeId(branchOffice.getId());
            ArrayList<OfficeImageEditableDTO> target = (ArrayList<OfficeImageEditableDTO>) officeImageMapper.toOfficeImageEditableDTOList(source);
            BranchOfficeEditableDTO editableDTO = BranchOfficeEditableDTO
                    .builder()
                    .id(branchOffice.getId())
                    .name(branchOffice.getName())
                    .address(branchOffice.getAddress())
                    .latitude(branchOffice.getLatitude())
                    .longitude(branchOffice.getLongitude())
                    .images(target)
                    .build();
            return new ResponseEntity<>(editableDTO, HttpStatus.OK);
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

    @PatchMapping("/editBranchOffice")
    public ResponseEntity<?> editBranchOffice(@RequestBody BranchOfficeEditableDTO data) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/removeBranchOffice/{id}")
    public ResponseEntity<?> removeBranchOffice(@PathVariable short id){
        BranchOffice office = BranchOffice.builder()
                .id(id)
                .build();
        boolean success = branchOfficeService.removeBranchOffice(office);
        if(success) {
            return new ResponseEntity<>("Branch office removed", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Branch office removed", HttpStatus.BAD_REQUEST);
    }
}
