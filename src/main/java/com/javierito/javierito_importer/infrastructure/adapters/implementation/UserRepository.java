package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.userModels.User;
import com.javierito.javierito_importer.domain.models.userModels.UserList;
import com.javierito.javierito_importer.domain.ports.IUserDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IUserRepository;
import com.javierito.javierito_importer.infrastructure.entities.UserEntity;
import com.javierito.javierito_importer.infrastructure.mappers.UserMapper;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserDomainRepository {

    @Autowired
    private UserMapper userMapper;

    @PersistenceContext
    private EntityManager entityManager;

    private final IUserRepository userRepository;

    public UserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserList> getAll(Pageable pageable,
                                 @Nullable Short status,
                                 @Nullable String role,
                                 @Nullable Integer officeId,
                                 @Nullable String someName) {
        String sql = "SELECT * FROM ufc_get_users(:p_limit, :p_offset, :p_status, :p_role, :p_office, :p_some_name)";
        Query query = entityManager.createNativeQuery(sql, UserList.class);
        query.setParameter("p_limit", pageable.getPageSize()); //limite e registros
        query.setParameter("p_offset", pageable.getPageNumber()); //a partir de
        query.setParameter("p_status", status);
        query.setParameter("p_role", role);
        query.setParameter("p_office", officeId);
        query.setParameter("p_some_name", someName);
        List<UserList> users = query.getResultList();
        return users;
    }

    @Override
    public User saveUser(User user) {
        var toEntity = userMapper.toUserEntity(user);
        var userCreated = userRepository.save(toEntity);
        return userMapper.toUser(userCreated);
    }

    @Override
    public User getByEmail(String email) {
        Optional<UserEntity> entity = userRepository.findByEmail(email);
        if(entity.isPresent()){
            User model = userMapper.toUser(entity.get());
            return model;
        }
        return null;
    }

    @Override
    public User getById(long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        User model = userMapper.toUser(entity.get());
        return model;
    }

    @Override
    public long countUsers() {
        return userRepository.count();
    }

    public long countActiveUsers() {
        return userRepository.countActiveUsers();
    }

    public long countInactiveUsers() {
        return userRepository.countInactiveUsers();
    }
}
