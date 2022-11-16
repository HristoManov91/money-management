package com.example.money_management_be.service.impl;

import com.example.money_management_be.dto.ProductTypeDto;
import com.example.money_management_be.entity.ProductTypeEntity;
import com.example.money_management_be.mapper.ProductTypeMapper;
import com.example.money_management_be.mapper.ResourceEntityTransformer;
import com.example.money_management_be.repository.BaseRepository;
import com.example.money_management_be.repository.ProductTypeRepository;
import com.example.money_management_be.service.ProductTypeService;
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
