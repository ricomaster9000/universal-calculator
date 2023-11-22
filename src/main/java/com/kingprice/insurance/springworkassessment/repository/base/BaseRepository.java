package com.kingprice.insurance.springworkassessment.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    @Transactional
    default List<T> saveAllEntitiesImmediately(Iterable<T> entities) {
        List<T> savedEntities = saveAll(entities);
        flush(); // Flush to persist immediately to the database.
        return savedEntities;
    }
}
