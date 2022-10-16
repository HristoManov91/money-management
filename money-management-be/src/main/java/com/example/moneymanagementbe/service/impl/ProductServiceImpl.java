package com.example.moneymanagementbe.service.impl;

import com.example.moneymanagementbe.dto.ProductDto;
import com.example.moneymanagementbe.entity.ProductEntity;
import com.example.moneymanagementbe.mapper.ProductMapper;
import com.example.moneymanagementbe.mapper.ResourceEntityTransformer;
import com.example.moneymanagementbe.repository.BaseRepository;
import com.example.moneymanagementbe.repository.ProductRepository;
import com.example.moneymanagementbe.service.ProductService;
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
