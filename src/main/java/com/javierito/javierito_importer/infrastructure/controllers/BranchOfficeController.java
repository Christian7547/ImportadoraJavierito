package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IBranchOfficeService;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOfficeDetails;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.ItemsByOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.OfficeList;
import com.javierito.javierito_importer.infrastructure.dtos.BranchOffice.*;
import com.javierito.javierito_importer.infrastructure.exception.types.BadRequestException;
import com.javierito.javierito_importer.infrastructure.exception.types.ResourceNotFoundException;
import com.javierito.javierito_importer.infrastructure.mappers.BranchOfficeImageMapper;
import com.javierito.javierito_importer.infrastructure.mappers.BranchOfficeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.javatuples.Pair;
import org.javatuples.Quartet;
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
            throw new ResourceNotFoundException("branchOffices");
        }
        long totalOffices = branchOfficeService.countBranchOffices();
        long actives = branchOfficeService.countActives();
        long inactives = branchOfficeService.countInactives();
        Quartet<List<OfficeList>, Long, Long, Long> data = Quartet.with(offices, totalOffices, actives, inactives);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/getBranchOfficeDetails/{branchOfficeId}")
    public ResponseEntity<?> getBranchOffice(@PathVariable int branchOfficeId){
        BranchOfficeDetails response = branchOfficeService.getDetails(branchOfficeId);
        if(response == null) {
            throw new ResourceNotFoundException("branchOffice", "id", Integer.toString(branchOfficeId));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
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
        var created = branchOfficeService.saveBranchOffice(branchOffice, newBranchOfficeDTO.getPathImages());
        if(created == null) {
            throw new BadRequestException("The branchOffice could not be created. Please try again later.");
        }
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
                .ownerId(data.getOwnerId())
                .status(data.getStatus())
                .build();
        var updated = branchOfficeService.saveBranchOffice(branchOffice, (ArrayList<String>) data.getImages());
        if(updated == null) {
            throw new BadRequestException("The branchOffice could not be updated. Please try again later.");
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteOffice/{id}")
    public ResponseEntity<?> deleteOffice(@PathVariable int id){
        boolean success = branchOfficeService.deleteBranchOffice(id);
        if(!success) {
            throw new ResourceNotFoundException("branchOffice", "id", Short.toString((short) id));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getCoordinates/{id}")
    public ResponseEntity<?> getCoordinatesByOffice(@PathVariable int id){
        Map<String, String> coordinates = branchOfficeService.getCoordinatesByOffice(id);
        if(coordinates.isEmpty())
            throw new ResourceNotFoundException("branchOffice", "id", Integer.toString(id));
        return new ResponseEntity<>(coordinates, HttpStatus.OK);
    }

    @PostMapping("/getItemsByOfficeId")
    public ResponseEntity<?> getItemsByOfficeId(@RequestParam(defaultValue = "5")int limit,
                                                @RequestParam(defaultValue = "1")int offset,
                                                @RequestBody ItemsByOfficeDTO body){
        long total = branchOfficeService.countItemsByOfficeId(body.getOfficeId());
        if(total <= 0) {
            throw new ResourceNotFoundException("Items in this branch office");
        }
        var items = branchOfficeService.getItemsByOfficeId(
                limit,
                offset,
                body.getOfficeId(),
                body.getParam(),
                body.getBrand(),
                body.getStatus());
        Pair<List<ItemsByOffice>, Long> data = Pair.with(items, total);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
