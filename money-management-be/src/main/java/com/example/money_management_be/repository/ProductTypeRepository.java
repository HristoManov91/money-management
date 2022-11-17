package com.example.money_management_be.repository;

import com.example.money_management_be.entity.ProductTypeEntity;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends BaseRepository<ProductTypeEntity, Long> {

    @EntityGraph(value = "join-category")
    Optional<ProductTypeEntity> findByNameAndBrandAndUserId(String name, String brand, Long userId);

    @EntityGraph(value = "join-category")
    Page<ProductTypeEntity> findAll(Predicate predicate, Pageable pageable);
}
