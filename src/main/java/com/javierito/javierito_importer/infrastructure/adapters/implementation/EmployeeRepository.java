package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.EmployeeModels.Employee;
import com.javierito.javierito_importer.domain.models.EmployeeModels.Manager;
import com.javierito.javierito_importer.domain.ports.IEmployeeDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IEmployeeRepository;
import com.javierito.javierito_importer.infrastructure.entities.EmployeeEntity;
import com.javierito.javierito_importer.infrastructure.mappers.EmployeeMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository implements IEmployeeDomainRepository {

    @Autowired
    private EmployeeMapper employeeMapper;

    private final IEmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeRepository(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        var toEntity = employeeMapper.toEmployeeEntity(employee);
        var employeeCreated = employeeRepository.save(toEntity);
        return employeeMapper.toEmployee(employeeCreated);
    }

    @Override
    public Employee getByUserId(long userId) {
        Optional<EmployeeEntity> entity = employeeRepository.getByUserId(userId);
        return employeeMapper.toEmployee(entity.get());
    }

    @Override
    public List<Manager> getManagers() {
        String sql = "SELECT * FROM ufc_get_managers()";
        Query query = entityManager.createNativeQuery(sql, Manager.class);
        List<Manager> result = query.getResultList();
        if(result.isEmpty()) {
            return new ArrayList<>();
        }
        return result;
    }
}
