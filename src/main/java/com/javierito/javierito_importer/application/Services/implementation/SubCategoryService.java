package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.ISubCategoryService;
import com.javierito.javierito_importer.domain.models.SubCategory;
import com.javierito.javierito_importer.domain.ports.ISubCategoryDomainRepository;

import java.util.ArrayList;

public class SubCategoryService implements ISubCategoryService {

    private final ISubCategoryDomainRepository subCategoryDomainRepository;

    public SubCategoryService(ISubCategoryDomainRepository subCategoryDomainRepository) { this.subCategoryDomainRepository = subCategoryDomainRepository; }


    @Override
    public ArrayList<SubCategory> getAllSubCategories() {

        return subCategoryDomainRepository.getSubCategories();

    }
}
