package com.example.money_management_be.repository;

import com.example.money_management_be.entity.ProductEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Long> {

    @EntityGraph(value = "join-productType")
    Page<ProductEntity> findAll(Predicate predicate, Pageable pageable);
}
