package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.ItemAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemAddressRepository extends JpaRepository<ItemAddressEntity, Short> {
}
