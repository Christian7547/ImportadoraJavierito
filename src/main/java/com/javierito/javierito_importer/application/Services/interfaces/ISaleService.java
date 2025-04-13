package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.SaleModels.SalesDetails;
import java.time.LocalDateTime;
import java.util.List;

public interface ISaleService {

    List<SalesDetails> getSalesReport(LocalDateTime from, LocalDateTime to);

}
