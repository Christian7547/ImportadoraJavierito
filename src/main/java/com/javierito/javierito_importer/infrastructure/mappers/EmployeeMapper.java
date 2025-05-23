package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.EmployeeModels.Employee;
import com.javierito.javierito_importer.infrastructure.entities.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeEntity target);
    EmployeeEntity toEmployeeEntity(Employee target);
}
