package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.interfaces.IBranchOfficeImageService;
import com.javierito.javierito_importer.domain.models.BranchOfficeImage;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeImageDomainRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class BranchOfficeImageService implements IBranchOfficeImageService{

    private final IBranchOfficeImageDomainRepository branchOfficeImageDomainRepository;

    @Override
    public ArrayList<BranchOfficeImage> getImagesByBranchOfficeId(int branchOfficeId) {
        return (ArrayList<BranchOfficeImage>)
                branchOfficeImageDomainRepository.getImagesByBranchOfficeId(branchOfficeId);
    }

    @Override
    public BranchOfficeImage createBranchOfficeImage(BranchOfficeImage branchOfficeImage) {
        return branchOfficeImageDomainRepository.createBranchOfficeImage(branchOfficeImage);
    }
}
