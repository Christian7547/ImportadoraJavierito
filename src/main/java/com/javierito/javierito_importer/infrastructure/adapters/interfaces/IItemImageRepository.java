package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.ItemImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemImageRepository extends JpaRepository<ItemImageEntity, Long> {
}
