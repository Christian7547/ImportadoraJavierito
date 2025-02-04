package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.Category;
import com.javierito.javierito_importer.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryEntity entity);

    CategoryEntity toCategoryEntity(Category category);

    ArrayList<Category> toCategories(List<CategoryEntity> entities);
}
