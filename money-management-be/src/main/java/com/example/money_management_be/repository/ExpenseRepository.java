package com.example.money_management_be.repository;

import com.example.money_management_be.entity.ExpenseEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends BaseRepository<ExpenseEntity, Long> {

    @EntityGraph(attributePaths = {"products", "products.productType", "products.productType.productCategory", "category", "user", "store", "store.address",
        "subCategory"})
//    @EntityGraph(value = "join-all")
    Page<ExpenseEntity> findAll(Predicate predicate, Pageable pageable);
}
