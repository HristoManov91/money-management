package com.example.moneymanagementbe.service.impl;

import com.example.moneymanagementbe.dto.ProductTypeDto;
import com.example.moneymanagementbe.entity.ProductTypeEntity;
import com.example.moneymanagementbe.mapper.ProductTypeMapper;
import com.example.moneymanagementbe.mapper.ResourceEntityTransformer;
import com.example.moneymanagementbe.repository.BaseRepository;
import com.example.moneymanagementbe.repository.ProductTypeRepository;
import com.example.moneymanagementbe.service.ProductTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductTypeServiceImpl implements ProductTypeService {

    ProductTypeRepository repository;
    ProductTypeMapper mapper;

    @Override
    public BaseRepository<ProductTypeEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<ProductTypeDto, ProductTypeEntity> resourceTransformer() {
        return mapper;
    }
}
