package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IBranchOfficeImageService;
import com.javierito.javierito_importer.application.Services.interfaces.IBranchOfficeService;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.OfficeList;
import com.javierito.javierito_importer.domain.models.BranchOfficeImage;
import com.javierito.javierito_importer.infrastructure.dtos.BranchOffice.*;
import com.javierito.javierito_importer.infrastructure.exception.types.ResourceNotFoundException;
import com.javierito.javierito_importer.infrastructure.mappers.BranchOfficeImageMapper;
import com.javierito.javierito_importer.infrastructure.mappers.BranchOfficeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/branchOffice")
@RequiredArgsConstructor
public class BranchOfficeController {

    private final BranchOfficeMapper branchOfficeMapper;
    private final BranchOfficeImageMapper officeImageMapper;

    private final IBranchOfficeService branchOfficeService;
    private final IBranchOfficeImageService imageService;

    @PostMapping("/getAll")
    public ResponseEntity<?> getBranchOffices(@RequestParam(defaultValue = "5")int limit,
                                              @RequestParam(defaultValue = "1")int offset,
                                              @RequestBody ParamsOfficeDTO params){
        ArrayList<OfficeList> offices = branchOfficeService.getAll(
                limit,
                offset,
                params.getQuery(),
                params.getStatus());
        if(offices.isEmpty()){
            throw new ResourceNotFoundException("branchOffice");
        }
        long totalOffices = branchOfficeService.countBranchOffices();
        Pair<List<OfficeList>, Long> data = Pair.of(offices, totalOffices);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/getBranchOfficeDetails/{branchOfficeId}")
    public ResponseEntity<?> getBranchOffice(@PathVariable int branchOfficeId){
        BranchOffice branchOffice = branchOfficeService.getById(branchOfficeId);
        if(branchOffice == null){
            throw new ResourceNotFoundException("branchOffice", "id", Integer.toString(branchOfficeId));
        }
        List<BranchOfficeImage> source = imageService.getImagesByBranchOfficeId(branchOffice.getId());
        ArrayList<OfficeImageEditableDTO> target = (ArrayList<OfficeImageEditableDTO>) officeImageMapper.toOfficeImageEditableDTOList(source);
        BranchOfficeEditableDTO editableDTO = BranchOfficeEditableDTO
                .builder()
                .id(branchOffice.getId())
                .name(branchOffice.getName())
                .address(branchOffice.getAddress())
                .latitude(branchOffice.getLatitude())
                .longitude(branchOffice.getLongitude())
                .registerDate(branchOffice.getRegisterDate())
                .images(target)
                .build();
        return new ResponseEntity<>(editableDTO, HttpStatus.OK);
    }

    @PostMapping("/createBranchOffice")
    public ResponseEntity<?> createBranchOffice(@RequestBody @Valid NewBranchOfficeDTO newBranchOfficeDTO) {
        BranchOffice branchOffice = BranchOffice.builder()
                .name(newBranchOfficeDTO.getName())
                .address(newBranchOfficeDTO.getAddress())
                .latitude(newBranchOfficeDTO.getLatitude())
                .longitude(newBranchOfficeDTO.getLongitude())
                .ownerId(newBranchOfficeDTO.getOwnerId())
                .status(newBranchOfficeDTO.getStatus())
                .build();
        var created = branchOfficeService.createBranchOffice(branchOffice, newBranchOfficeDTO.getPathImages());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PatchMapping("/editBranchOffice")
    public ResponseEntity<?> editBranchOffice(@RequestBody @Valid BranchOfficeEditableDTO data) {
        BranchOffice branchOffice = BranchOffice.builder()
                .id(data.getId())
                .name(data.getName())
                .address(data.getAddress())
                .latitude(data.getLatitude())
                .longitude(data.getLongitude())
                .registerDate(data.getRegisterDate())
                .build();
        branchOfficeService.updateBranchOffice(branchOffice);
        List<BranchOfficeImage> images = officeImageMapper.toOfficeImageFromEditableDTOList(data.getImages());
        imageService.checkIfImagesStatus(images, branchOffice.getId());
        return new ResponseEntity<>(images,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/removeBranchOffice/{id}")
    public ResponseEntity<?> removeBranchOffice(@PathVariable short id){
        BranchOffice office = BranchOffice.builder()
                .id(id)
                .build();
        boolean success = branchOfficeService.removeBranchOffice(office);
        if(!success) {
            throw new ResourceNotFoundException("branchOffice", "id", Short.toString(id));
        }
        return new ResponseEntity<>("Branch office removed", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getCoordinates/{id}")
    public ResponseEntity<?> getCoordinatesByOffice(@PathVariable int id){
        Map<String, String> coordinates = branchOfficeService.getCoordinatesByOffice(id);
        if(coordinates.isEmpty())
            throw new ResourceNotFoundException("branchOffice", "id", Integer.toString(id));
        return new ResponseEntity<>(coordinates, HttpStatus.OK);
    }
}
