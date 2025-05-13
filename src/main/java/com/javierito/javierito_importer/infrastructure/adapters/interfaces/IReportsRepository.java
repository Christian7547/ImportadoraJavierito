package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReportsRepository extends JpaRepository<ReportEntity, Integer> {
}
