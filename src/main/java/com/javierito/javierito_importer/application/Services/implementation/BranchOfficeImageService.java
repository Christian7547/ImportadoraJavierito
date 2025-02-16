package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IBranchOfficeImageService;
import com.javierito.javierito_importer.domain.models.BranchOfficeImage;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeImageDomainRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void removeBranchOfficeImage(int imageId) {
        branchOfficeImageDomainRepository.removeBranchOfficeImage(imageId);
    }

    @Override
    public void checkIfImagesStatus(List<BranchOfficeImage> images, int officeId) {
        if(images.isEmpty())
            return;
        for(BranchOfficeImage image : images){
            if(!existsByPath(image.getPathImage())){
                image.setBranchOfficeID(officeId);
                createBranchOfficeImage(image);
            } else if(existsByPath(image.getPathImage()) && image.getStatus() == 0){
                removeBranchOfficeImage(image.getId());
            }
        }
    }

    @Override
    public boolean existsByPath(String path) {
        return branchOfficeImageDomainRepository.existsByPath(path);
    }
}
