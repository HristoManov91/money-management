package com.example.money_management_be.repository;

import com.example.money_management_be.entity.ExpenseSubCategoryEntity;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseSubCategoryRepository extends BaseRepository<ExpenseSubCategoryEntity, Long> {

    Optional<ExpenseSubCategoryEntity> findByNameAndExpenseCategoryId(String name, Long categoryId);
}
