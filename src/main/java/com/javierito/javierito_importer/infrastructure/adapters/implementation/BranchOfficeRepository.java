package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.BranchOffice;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IBranchOfficeRepository;
import com.javierito.javierito_importer.infrastructure.mappers.BranchOfficeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BranchOfficeRepository implements IBranchOfficeDomainRepository {

    @Autowired
    private BranchOfficeMapper branchOfficeMapper;

    private final IBranchOfficeRepository branchOfficeRepository;

    @Override
    public List<BranchOffice> getAll() {
        var branchOffices = branchOfficeRepository.getAll();
        if(branchOffices.isEmpty()){
            return new ArrayList<>();
        }
        return branchOfficeMapper.toBranchOffices(branchOffices);
    }

    @Override
    public BranchOffice getById(int id) {
        var branchOfficeEntity = branchOfficeRepository.getBranchOfficeById(id);
        return branchOfficeMapper.toBranchOffice(branchOfficeEntity);
    }

    @Override
    public BranchOffice createBranchOffice(BranchOffice branchOffice) {
        var toEntity = branchOfficeMapper.toBranchOfficeEntity(branchOffice);
        var created = branchOfficeRepository.save(toEntity);
        return branchOfficeMapper.toBranchOffice(created);
    }

    @Override
    public void removeBranchOffice(BranchOffice branchOffice) {
        var branchOfficeEntity = branchOfficeRepository.findById(branchOffice.getId());
        if(branchOfficeEntity.isPresent()){
            branchOfficeMapper.saveChanges(branchOfficeEntity.get(), branchOfficeMapper.toBranchOfficeEntity(branchOffice));
            branchOfficeRepository.save(branchOfficeEntity.get());
        }
    }
}
