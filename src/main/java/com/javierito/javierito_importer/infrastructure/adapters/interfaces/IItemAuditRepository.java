package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.ItemAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemAuditRepository extends JpaRepository<ItemAuditEntity, Long> {
}
