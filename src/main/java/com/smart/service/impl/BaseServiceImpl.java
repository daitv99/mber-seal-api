package com.smart.service.impl;

import com.smart.component.util.ObjectMappingUtils;
import com.smart.dto.EmployeeDto;
import com.smart.entity.BaseEntity;
import com.smart.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public class BaseServiceImpl<T extends BaseEntity, ID> implements BaseService<T, ID> {

    protected JpaRepository<T, ID> repository;

    public BaseServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T getById(ID id) {
        T t = repository.findById(id).orElse(null);
        return t == null || t.isDeleted() ? null : t;
    }

    @Override
    @Transactional
    public T creatOrUpdate(ID id, T t) {
        if (id != null) {
            T e = this.getById(id);
            if (e != null) {
                ObjectMappingUtils.copyProperties(t, e, getCurrentUserId(), "id", "createdAt", "createdBy", "isDeleted");
                return repository.save(e);
            } else {
                return null;
            }
        }
        ObjectMappingUtils.copyProperties(t, t, getCurrentUserId(), "id", "createdAt", "createdBy", "isDeleted");
        return repository.save(t);
    }

    @Override
    public boolean deleteById(ID id) {
        try {
            T t = this.getById(id);
            t.setDeleted(true);
            repository.save(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Long getCurrentUserId() {
        EmployeeDto currentUser = null;
//        try {
//            currentUser = (EmployeeDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        } catch (Exception ignored) {
//        }
        return currentUser == null ? 0L : currentUser.getEmployeeId();
    }
}
