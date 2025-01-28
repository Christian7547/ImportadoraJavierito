package com.javierito.javierito_importer.infrastructure.mappers;


import com.javierito.javierito_importer.domain.models.SubCategory;
import com.javierito.javierito_importer.infrastructure.entities.SubCategoryEntity;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {

    ArrayList<SubCategory> toSubCategories(List<SubCategoryEntity> target);

}
