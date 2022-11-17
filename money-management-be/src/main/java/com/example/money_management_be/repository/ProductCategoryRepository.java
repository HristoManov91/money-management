package com.example.money_management_be.repository;

import com.example.money_management_be.entity.ProductCategoryEntity;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends BaseRepository<ProductCategoryEntity, Long>{

    Optional<ProductCategoryEntity> findByNameAndUserId(String name, Long userId);
}
