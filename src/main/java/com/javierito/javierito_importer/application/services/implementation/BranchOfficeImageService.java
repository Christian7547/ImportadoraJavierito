package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.interfaces.IBranchOfficeImageService;
import com.javierito.javierito_importer.domain.models.BranchOfficeImage;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeImageDomainRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BranchOfficeImageService implements IBranchOfficeImageService{

    private final IBranchOfficeImageDomainRepository branchOfficeImageDomainRepository;

    @Override
    public BranchOfficeImage createBranchOfficeImage(BranchOfficeImage branchOfficeImage) {
        return branchOfficeImageDomainRepository.createBranchOfficeImage(branchOfficeImage);
    }
}
