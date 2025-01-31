package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.BranchOffice;

import java.util.List;

public interface IBranchOfficeDomainRepository {
    List<BranchOffice> getAll();
    BranchOffice getById(int id);
    BranchOffice createBranchOffice(BranchOffice branchOffice);
    BranchOffice removeBranchOffice(BranchOffice branchOffice);
}
