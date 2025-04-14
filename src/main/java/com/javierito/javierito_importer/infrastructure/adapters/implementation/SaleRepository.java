package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.ports.ISaleDomainRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class SaleRepository implements ISaleDomainRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long createSale(double total,
                           long employeeId,
                           long clientId,
                           double commission,
                           double discount,
                           String details) {
        String sql = "SELECT * FROM ufc_new_sale(:p_total, :p_employeeid, :p_clientid, :p_commission, :p_discount, CAST(:p_detail AS JSONB));";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("p_total", BigDecimal.valueOf(total));
        query.setParameter("p_employeeid", employeeId);
        query.setParameter("p_clientid", clientId);
        query.setParameter("p_commission", BigDecimal.valueOf(commission));
        query.setParameter("p_discount", BigDecimal.valueOf(discount));
        query.setParameter("p_detail", details);
        Long out = (Long) query.getSingleResult();
        return out.longValue();
    }
}
