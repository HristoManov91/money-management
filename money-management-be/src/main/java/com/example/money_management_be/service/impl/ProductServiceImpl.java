package com.example.money_management_be.service.impl;

import com.example.money_management_be.dto.ProductDto;
import com.example.money_management_be.entity.ProductEntity;
import com.example.money_management_be.mapper.ProductMapper;
import com.example.money_management_be.mapper.ResourceEntityTransformer;
import com.example.money_management_be.repository.BaseRepository;
import com.example.money_management_be.repository.ProductRepository;
import com.example.money_management_be.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductRepository repository;
    ProductMapper mapper;

    @Override
    public BaseRepository<ProductEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<ProductDto, ProductEntity> resourceTransformer() {
        return mapper;
    }
}
