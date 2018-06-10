package com.excelsiorious.library.repository;

import com.excelsiorious.library.entity.BasicEntity;

import java.util.List;
import java.util.Optional;

public interface BasicRepository<E extends BasicEntity> {
    boolean create(E entity);

    Optional<E> find(long id);

    Optional<E> findByName(String... name);

    boolean update(E entity);

    boolean delete(E entity);

    List<E> findAll();
}
