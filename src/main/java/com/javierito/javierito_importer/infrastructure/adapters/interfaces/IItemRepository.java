package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<ItemEntity, Long> {

}
