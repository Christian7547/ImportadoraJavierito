package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.Brand;
import java.util.ArrayList;

public interface IBrandDomainRepository {

    ArrayList<Brand> getBrands();

}
