package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.OfficeList;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IBranchOfficeRepository;
import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeEntity;
import com.javierito.javierito_importer.infrastructure.mappers.BranchOfficeMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BranchOfficeRepository implements IBranchOfficeDomainRepository {

    @Autowired
    private BranchOfficeMapper branchOfficeMapper;

    @PersistenceContext
    private EntityManager entityManager;

    private final IBranchOfficeRepository branchOfficeRepository;

    @Override
    public List<OfficeList> getAll(Pageable pageable,
                                   @Nullable String param,
                                   @Nullable Integer status) {
        String sql = "SELECT * FROM ufc_get_offices(:p_limit, :p_offset, :p_param, :p_status)";
        Query query = entityManager.createNativeQuery(sql, OfficeList.class);
        query.setParameter("p_limit", pageable.getPageSize());
        query.setParameter("p_offset", pageable.getPageNumber());
        query.setParameter("p_param", param);
        query.setParameter("p_status", status);
        List<OfficeList> result = query.getResultList();
        if(result.isEmpty()){
            return new ArrayList<>();
        }
        return result;
    }

    @Override
    public BranchOffice getById(int id) {
        var branchOfficeEntity = branchOfficeRepository.getBranchOfficeById(id);
        return branchOfficeMapper.toBranchOffice(branchOfficeEntity.get());
    }

    @Override
    public BranchOffice createBranchOffice(BranchOffice branchOffice) {
        var toEntity = branchOfficeMapper.toBranchOfficeEntity(branchOffice);
        var created = branchOfficeRepository.save(toEntity);
        return branchOfficeMapper.toBranchOffice(created);
    }

    @Override
    public BranchOffice removeOrEditBranchOffice(BranchOffice branchOffice) {
        BranchOfficeEntity toEntity = branchOfficeMapper.toBranchOfficeEntity(branchOffice);
        BranchOfficeEntity saved = branchOfficeRepository.save(toEntity);
        return branchOfficeMapper.toBranchOffice(saved);
    }

    @Override
    public long countBranchOffices() {
        return branchOfficeRepository.countBranchOffices();
    }
}
