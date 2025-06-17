package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOfficeDetails;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.ItemsByOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.OfficeList;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IBranchOfficeDomainRepository {
    List<OfficeList> getAll(Pageable pageable, @Nullable String query, @Nullable Integer status);
    BranchOffice getById(int id);
    BranchOfficeDetails getDetails(int id);
    BranchOffice save(BranchOffice branchOffice, List<String> images);
    void changeStatus(BranchOffice branchOffice);
    long countBranchOffices();
    long countInactives();
    long countActives();
    List<ItemsByOffice> getItemsByOfficeId(Pageable pageable, int officeId, @Nullable String param, @Nullable String brand, @Nullable String status);
    long countItemsByOfficeId(int officeId);
}
