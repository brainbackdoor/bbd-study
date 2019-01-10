package com.example.demo;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class SimpleMyRepository<T, ID extends Serializable> extends QuerydslJpaRepository<T, ID> implements MyRepository<T, ID> {
//public class SimpleMyRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {

    private EntityManager entityManager;


    public SimpleMyRepository(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public boolean contains(T entity) {
        return entityManager.contains(entity);
    }
}
