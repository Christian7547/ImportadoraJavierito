package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.Brand;
import com.javierito.javierito_importer.infrastructure.entities.BrandEntity;
import org.mapstruct.Mapper;


import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toBrand(BrandEntity entity);

    BrandEntity toBrandEntity(Brand brand);

    ArrayList<Brand> toBrands(List<BrandEntity> target);

}
