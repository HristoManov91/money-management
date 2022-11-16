package com.example.money_management_be.repository;

import com.example.money_management_be.entity.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Long> {

}
