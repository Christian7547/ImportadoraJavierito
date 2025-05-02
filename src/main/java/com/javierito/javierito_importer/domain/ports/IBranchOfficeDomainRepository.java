package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.OfficeList;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IBranchOfficeDomainRepository {
    List<OfficeList> getAll(int limit, int offset, @Nullable String name, @Nullable String address);
    BranchOffice getById(int id);
    BranchOffice createBranchOffice(BranchOffice branchOffice);
    BranchOffice removeOrEditBranchOffice(BranchOffice branchOffice);
    long countBranchOffices();
}
