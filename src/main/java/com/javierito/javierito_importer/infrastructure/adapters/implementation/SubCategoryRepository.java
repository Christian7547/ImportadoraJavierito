package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.SubCategory;
import com.javierito.javierito_importer.domain.ports.ISubCategoryDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.ISubCategoryRepository;
import com.javierito.javierito_importer.infrastructure.mappers.SubCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class SubCategoryRepository implements ISubCategoryDomainRepository {

    @Autowired
    private SubCategoryMapper subCategoryMapper;

    private final ISubCategoryRepository subCategoryRepository;

    public SubCategoryRepository(ISubCategoryRepository subCategoryRepository) {this.subCategoryRepository = subCategoryRepository;}

    @Override
    public ArrayList<SubCategory> getSubCategories() {

        return this.subCategoryMapper.toSubCategories(this.subCategoryRepository.findAll());
    }
}
