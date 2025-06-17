package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOfficeDetails;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.ItemsByOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeModels.OfficeList;
import com.javierito.javierito_importer.domain.ports.IBranchOfficeDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IBranchOfficeRepository;
import com.javierito.javierito_importer.infrastructure.mappers.BranchOfficeMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        var result = branchOfficeRepository.findById(id);
        return result.map(branchOfficeEntity -> branchOfficeMapper.toBranchOffice(branchOfficeEntity)).orElse(null);
    }

    @Override
    public BranchOfficeDetails getDetails(int id) {
        String sql = "SELECT * FROM ufc_get_office_by_id(:p_id)";
        Query query = entityManager.createNativeQuery(sql, BranchOfficeDetails.class);
        query.setParameter("p_id", id);
        return (BranchOfficeDetails) query.getSingleResult();
    }

    @Transactional
    @Override
    public BranchOffice save(BranchOffice branchOffice, List<String> images) {
        String sql = "SELECT * FROM ufc_save_office(:p_name, :p_address, :p_latitude, :p_longitude, :p_owner_id, :p_paths, :p_status)";
        Query query = entityManager.createNativeQuery(sql, BranchOffice.class);
        if(branchOffice.getId() != null) {
            sql = "SELECT * FROM ufc_save_office(:p_name, :p_address, :p_latitude, :p_longitude, :p_owner_id, :p_paths, :p_status, :p_id)";
            query = entityManager.createNativeQuery(sql, BranchOffice.class);
            query.setParameter("p_id", branchOffice.getId());
        }
        query.setParameter("p_name", branchOffice.getName());
        query.setParameter("p_address", branchOffice.getAddress());
        query.setParameter("p_latitude", branchOffice.getLatitude());
        query.setParameter("p_longitude", branchOffice.getLongitude());
        query.setParameter("p_owner_id", branchOffice.getOwnerId());
        query.setParameter("p_paths", images.toArray(new String[0]));
        query.setParameter("p_status", branchOffice.getStatus());

        return (BranchOffice) query.getSingleResult();
    }

    @Override
    public void changeStatus(BranchOffice branchOffice) {
        var entity = branchOfficeMapper.toBranchOfficeEntity(branchOffice);
        branchOfficeRepository.save(entity);
    }

    @Override
    public long countBranchOffices() {
        return branchOfficeRepository.countBranchOffices();
    }

    @Override
    public long countInactives() {
        return branchOfficeRepository.countActivesBranchOffices();
    }

    @Override
    public long countActives() {
        return branchOfficeRepository.countInactivesBranchOffices();
    }

    @Override
    public List<ItemsByOffice> getItemsByOfficeId(Pageable pageable, int officeId, @Nullable String param, @Nullable String brand, @Nullable String status) {
        String sql = "SELECT * FROM ufc_get_items_by_office(:p_limit, :p_offset, :p_office_id, :p_param, :p_brand, :p_status)";
        Query query = entityManager.createNativeQuery(sql, ItemsByOffice.class);
        query.setParameter("p_limit", pageable.getPageSize());
        query.setParameter("p_offset", pageable.getPageNumber());
        query.setParameter("p_office_id", officeId);
        query.setParameter("p_param", param);
        query.setParameter("p_brand", brand);
        query.setParameter("p_status", status);
        List<ItemsByOffice> result = query.getResultList();
        if(result.isEmpty()){
            return new ArrayList<>();
        }
        return result;
    }

    @Override
    public long countItemsByOfficeId(int officeId) {
        return branchOfficeRepository.countItemsByOfficeId(officeId);
    }
}
