package com.example.moneymanagementbe.repository;

import com.example.moneymanagementbe.entity.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Long> {

}
