package com.javierito.javierito_importer.application.Services.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.domain.models.Sale;

public interface ISaleService {
    long createSale(Sale sale) throws JsonProcessingException;
}
