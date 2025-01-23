package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.BranchOfficeImage;

import java.util.List;

public interface IBranchOfficeImageDomainRepository {
    BranchOfficeImage createBranchOfficeImage(BranchOfficeImage branchOfficeImage);
    List<BranchOfficeImage> getImagesByBranchOfficeId(int branchOfficeId);
    void removeBranchOfficeImage(BranchOfficeImage branchOfficeImage);
}
