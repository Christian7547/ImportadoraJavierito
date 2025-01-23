package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.interfaces.IBranchOfficeService;
import com.javierito.javierito_importer.domain.models.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeImage;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeDomainRepository;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeImageDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BranchOfficeService implements IBranchOfficeService {

    private final IBranchOfficeDomainRepository branchOfficeDomainRepository;
    private final IBranchOfficeImageDomainRepository branchOfficeImageDomainRepository;

    @Override
    public ArrayList<BranchOffice> getAll() {
        return (ArrayList<BranchOffice>) branchOfficeDomainRepository.getAll();
    }

    @Override
    public BranchOffice getById(int id) {
        return branchOfficeDomainRepository.getById(id);
    }

    @Transactional
    @Override
    public BranchOffice createBranchOffice(BranchOffice branchOffice, ArrayList<String> pathImages) {
        List<BranchOfficeImage> images = new ArrayList<>();
        var branchOfficeCreated = branchOfficeDomainRepository.createBranchOffice(branchOffice);
        for(String pathImage : pathImages) {
            BranchOfficeImage image = BranchOfficeImage.builder()
                    .pathImage(pathImage)
                    .branchOfficeID(branchOfficeCreated.getId())
                    .build();
            images.add(image);
        }
        for(BranchOfficeImage image : images) {
            branchOfficeImageDomainRepository.createBranchOfficeImage(image);
        }
        return branchOfficeCreated;
    }
}
