package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.SubCategory;
import java.util.ArrayList;

public interface ISubCategoryDomainRepository {

    ArrayList<SubCategory> getSubCategories();

}
