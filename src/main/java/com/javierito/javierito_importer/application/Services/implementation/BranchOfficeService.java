package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IBranchOfficeService;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.OfficeList;
import com.javierito.javierito_importer.domain.models.BranchOfficeImage;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeDomainRepository;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeImageDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class BranchOfficeService implements IBranchOfficeService {

    private final IBranchOfficeDomainRepository branchOfficeDomainRepository;
    private final IBranchOfficeImageDomainRepository branchOfficeImageDomainRepository;

    @Override
    public ArrayList<OfficeList> getAll(int limit,
                                        int offset,
                                        @Nullable String query,
                                        @Nullable Integer status) {
        Pageable pageable = PageRequest.of(offset, limit);;
        return (ArrayList<OfficeList>) branchOfficeDomainRepository.getAll(pageable,
                query,
                status);
    }

    @Override
    public BranchOffice getById(int id) {
        return branchOfficeDomainRepository.getById(id);
    }

    @Transactional
    @Override
    public BranchOffice createBranchOffice(BranchOffice branchOffice, ArrayList<String> pathImages) {
        List<BranchOfficeImage> images = new ArrayList<>();
        var branchOfficeCreated = branchOfficeDomainRepository.save(branchOffice);
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

    @Override
    public BranchOffice updateBranchOffice(BranchOffice branchOffice) {
        branchOffice.setLastUpdate(LocalDateTime.now());
        BranchOffice response = branchOfficeDomainRepository.save(branchOffice);
        if(response != null){
            return response;
        }
        throw new RuntimeException();
    }

    @Override
    public Map<String, String> getCoordinatesByOffice(int branchOfficeId) {
        BranchOffice office = branchOfficeDomainRepository.getById(branchOfficeId);
        if(office != null){
            Map<String, String> coordinates = new HashMap<>();
            coordinates.put("latitude", office.getLatitude());
            coordinates.put("longitude", office.getLongitude());
            return coordinates;
        }
        return Map.of();
    }

    @Override
    public boolean changeStatus(BranchOffice branchOffice) {
        BranchOffice getOffice = getById(branchOffice.getId());
        if(getOffice != null) {
            getOffice.setStatus(branchOffice.getStatus());
            getOffice.setLastUpdate(LocalDateTime.now());
            branchOfficeDomainRepository.save(getOffice);
            return true;
        }
        return false;
    }

    @Override
    public long countBranchOffices() {
        return branchOfficeDomainRepository.countBranchOffices();
    }
}
