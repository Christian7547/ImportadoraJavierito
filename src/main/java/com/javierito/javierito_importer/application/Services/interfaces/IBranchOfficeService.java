package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOfficeDetails;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.ItemsByOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.OfficeList;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IBranchOfficeService {

    ArrayList<OfficeList> getAll(int limit, int offset, @Nullable String query, @Nullable Integer status);
    BranchOffice getById(int id);
    BranchOfficeDetails getDetails(int id);
    BranchOffice saveBranchOffice(BranchOffice branchOffice, List<String> pathImages);
    Map<String, String> getCoordinatesByOffice(int branchOfficeId);
    boolean deleteBranchOffice(int id);
    long countBranchOffices();
    long countInactives();
    long countActives();
    List<ItemsByOffice> getItemsByOfficeId(int limit, int offset, int officeId, @Nullable String param, @Nullable String brand, @Nullable String status);
    long countItemsByOfficeId(int officeId);
}
