package com.example.money_management_be.repository;

import com.example.money_management_be.entity.ExpenseCategoryEntity;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseCategoryRepository extends BaseRepository<ExpenseCategoryEntity, Long>{

    Optional<ExpenseCategoryEntity> findByNameAndUserId(String name, Long userId);
}
