package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.Brand;
import com.javierito.javierito_importer.domain.ports.IBrandDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IBranRepository;
import com.javierito.javierito_importer.infrastructure.mappers.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class BrandRepository implements IBrandDomainRepository {

    @Autowired
    private BrandMapper brandMapper;

    private final IBranRepository brandRepository;

    public BrandRepository(IBranRepository brandRepository) {this.brandRepository = brandRepository;}

    @Override
    public ArrayList<Brand> getBrands() {
        return this.brandMapper.toBrands(this.brandRepository.findAll());
    }

}
