package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.BranchOfficeImage;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeImageDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IBranchOfficeImageRepository;
import com.javierito.javierito_importer.infrastructure.mappers.BranchOfficeImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BranchOfficeImageRepository implements IBranchOfficeImageDomainRepository {

    @Autowired
    private BranchOfficeImageMapper branchOfficeImageMapper;

    private final IBranchOfficeImageRepository branchOfficeImageRepository;

    @Override
    public BranchOfficeImage createBranchOfficeImage(BranchOfficeImage branchOfficeImage) {
        var toEntity = branchOfficeImageMapper.toBranchOfficeImageEntity(branchOfficeImage);
        var created = branchOfficeImageRepository.save(toEntity);
        return branchOfficeImageMapper.toBranchOfficeImage(created);
    }

    @Override
    public List<BranchOfficeImage> getImagesByBranchOfficeId(int branchOfficeId) {
        var images = branchOfficeImageRepository.getImagesByBranchOfficeId(branchOfficeId);
        if(images.isEmpty()){
            return new ArrayList<>();
        }
        List<BranchOfficeImage> toModels = branchOfficeImageMapper.toBranchOfficeImages(images);
        return toModels;
    }

    @Override
    public void removeBranchOfficeImage(int imageId) {
        branchOfficeImageRepository.deleteById(imageId);
    }

    @Override
    public boolean existsByPath(String path) {
        return branchOfficeImageRepository.existsByPathBranchOffice(path);
    }
}
