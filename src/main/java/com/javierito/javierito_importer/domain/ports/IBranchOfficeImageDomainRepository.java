package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.BranchOfficeImage;

public interface IBranchOfficeImageDomainRepository {
    BranchOfficeImage createBranchOfficeImage(BranchOfficeImage branchOfficeImage);
    void removeBranchOfficeImage(BranchOfficeImage branchOfficeImage);
}
