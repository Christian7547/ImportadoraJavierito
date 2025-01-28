package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.Brand;
import com.javierito.javierito_importer.domain.models.ItemAddress;

import java.util.ArrayList;

public interface IItemAddressDomainRepository {

    ArrayList<ItemAddress> getItemAddresses();

}
