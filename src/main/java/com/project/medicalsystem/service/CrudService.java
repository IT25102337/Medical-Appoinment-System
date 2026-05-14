package com.project.medicalsystem.service;

import com.project.medicalsystem.model.BaseEntity;

import java.util.List;

public interface CrudService<T extends BaseEntity> {

    void add(T entity);


    List<T> getAll();


    void delete(String id);


    String generateId();


    default int getCount() {
        return getAll().size();
    }


    default boolean exists(String id) {
        return getAll().stream()
                .anyMatch(e -> e.getId().equals(id));
    }
}
