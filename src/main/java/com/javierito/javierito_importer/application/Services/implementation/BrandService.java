package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IBrandService;
import com.javierito.javierito_importer.domain.models.Brand;
import com.javierito.javierito_importer.domain.ports.IBrandDomainRepository;

import java.util.ArrayList;

public class BrandService implements IBrandService {

    private final IBrandDomainRepository brandDomainRepository;

    public BrandService(IBrandDomainRepository brandDomainRepository) {this.brandDomainRepository = brandDomainRepository;}

    @Override
    public ArrayList<Brand> getAllBrands() {
        return brandDomainRepository.getBrands();
    }
}
