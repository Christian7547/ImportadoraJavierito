package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.ISubCategoryService;
import com.javierito.javierito_importer.domain.models.SubCategory;
import com.javierito.javierito_importer.infrastructure.dtos.SubCategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/subCategories")
public class SubCategoryController {

    private final ISubCategoryService subCategoryService;

    public SubCategoryController(ISubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/getAllSubCategories")
    public ResponseEntity<?> getSubCategories() {
        try {
            List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
            List<SubCategoryDTO> subCategoryDTOs = subCategories.stream()
                    .map(subCategory ->
                            SubCategoryDTO.builder()
                                    .id(subCategory.getId())
                                    .name(subCategory.getName())
                                    .build()
                    )
                    .collect(Collectors.toList());
            return new ResponseEntity<>(subCategoryDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not get subcategories", HttpStatus.OK);
        }
    }
}
