package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IBranchOfficeService;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOfficeDetails;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.ItemsByOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.OfficeList;
import com.javierito.javierito_importer.domain.models.BranchOfficeImage;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeDomainRepository;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeImageDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

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
        Pageable pageable = PageRequest.of(offset, limit);
        return (ArrayList<OfficeList>) branchOfficeDomainRepository.getAll(pageable,
                query,
                status);
    }

    @Override
    public BranchOfficeDetails getDetails(int id) {
        BranchOfficeDetails office = branchOfficeDomainRepository.getDetails(id);
        var images = branchOfficeImageDomainRepository.getImagesByBranchOfficeId(office.getOfficeId())
                .stream()
                .map(BranchOfficeImage::getPathImage)
                .toList();
        office.setImages(images);
        return office;
    }

    @Override
    public BranchOffice getById(int id) {
        return branchOfficeDomainRepository.getById(id);
    }

    @Override
    public BranchOffice saveBranchOffice(BranchOffice branchOffice, List<String> pathImages) {
        return branchOfficeDomainRepository.save(branchOffice, pathImages);
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
    public boolean deleteBranchOffice(int id) {
        BranchOffice getOffice = getById(id);
        if(getOffice != null) {
            getOffice.setStatus((short) 2);
            getOffice.setLastUpdate(LocalDateTime.now());
            branchOfficeDomainRepository.changeStatus(getOffice);
            return true;
        }
        return false;
    }

    @Override
    public long countBranchOffices() {
        return branchOfficeDomainRepository.countBranchOffices();
    }

    @Override
    public long countInactives() {
        return branchOfficeDomainRepository.countInactives();
    }

    @Override
    public long countActives() {
        return branchOfficeDomainRepository.countActives();
    }

    @Override
    public List<ItemsByOffice> getItemsByOfficeId(int limit, int offset, int officeId, @Nullable String param, @Nullable String brand, @Nullable String status) {
        Pageable pageable = PageRequest.of(offset, limit);
        return branchOfficeDomainRepository.getItemsByOfficeId(pageable, officeId, param, brand, status);
    }

    @Override
    public long countItemsByOfficeId(int officeId) {
        return branchOfficeDomainRepository.countItemsByOfficeId(officeId);
    }
}
