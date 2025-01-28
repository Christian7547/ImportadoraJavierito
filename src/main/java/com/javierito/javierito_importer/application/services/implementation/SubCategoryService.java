package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.interfaces.ISubCategoryService;
import com.javierito.javierito_importer.domain.models.SubCategory;
import com.javierito.javierito_importer.domain.ports.ISubCategoryDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class SubCategoryService implements ISubCategoryService {

    private final ISubCategoryDomainRepository subCategoryDomainRepository;

    public SubCategoryService(ISubCategoryDomainRepository subCategoryDomainRepository) { this.subCategoryDomainRepository = subCategoryDomainRepository; }


    @Override
    public ArrayList<SubCategory> getAllSubCategories() {

        return subCategoryDomainRepository.getSubCategories();

    }
}
