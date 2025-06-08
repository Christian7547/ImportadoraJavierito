package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IItemRepository extends JpaRepository<ItemEntity, Long> {
    @Query("SELECT COUNT(i.id) FROM ItemEntity i WHERE i.status = 1")
    Integer countAll();

    @Query("SELECT COUNT(i.id) FROM ItemEntity i WHERE i.status = 0")
    Long countAllInRecycleBin();
}
