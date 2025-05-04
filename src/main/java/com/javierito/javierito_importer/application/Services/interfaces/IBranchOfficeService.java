package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.OfficeList;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Map;

public interface IBranchOfficeService {

    ArrayList<OfficeList> getAll(int limit, int offset, @Nullable String name, @Nullable String address);
    BranchOffice getById(int id);
    BranchOffice createBranchOffice(BranchOffice branchOffice, ArrayList<String> pathImages);
    BranchOffice updateBranchOffice(BranchOffice branchOffice);
    Map<String, String> getCoordinatesByOffice(int branchOfficeId);
    boolean removeBranchOffice(BranchOffice branchOffice);
    long countBranchOffices();
}
