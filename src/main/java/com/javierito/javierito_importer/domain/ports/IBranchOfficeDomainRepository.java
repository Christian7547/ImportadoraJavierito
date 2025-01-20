package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.BranchOffice;

public interface IBranchOfficeDomainRepository {
    BranchOffice createBranchOffice(BranchOffice branchOffice);
    void removeBranchOffice(BranchOffice branchOffice);
}
