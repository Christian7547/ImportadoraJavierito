package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.OfficeList;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IBranchOfficeDomainRepository {
    List<OfficeList> getAll(Pageable pageable, @Nullable String query, @Nullable Integer status);
    BranchOffice getById(int id);
    BranchOffice createBranchOffice(BranchOffice branchOffice);
    BranchOffice removeOrEditBranchOffice(BranchOffice branchOffice);
    long countBranchOffices();
}
