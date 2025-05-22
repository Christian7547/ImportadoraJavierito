package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.BranchOfficeImage;

import java.util.List;

public interface IBranchOfficeImageDomainRepository {
    List<BranchOfficeImage> getImagesByBranchOfficeId(int branchOfficeId);
}
