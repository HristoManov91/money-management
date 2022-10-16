package com.example.moneymanagementbe.service.impl;

import com.example.moneymanagementbe.dto.ProductNameDto;
import com.example.moneymanagementbe.entity.ProductNameEntity;
import com.example.moneymanagementbe.mapper.ProductNameMapper;
import com.example.moneymanagementbe.mapper.ResourceEntityTransformer;
import com.example.moneymanagementbe.repository.BaseRepository;
import com.example.moneymanagementbe.repository.ProductNameRepository;
import com.example.moneymanagementbe.service.ProductNameService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductNameServiceImpl implements ProductNameService {

    ProductNameRepository repository;
    ProductNameMapper mapper;

    @Override
    public BaseRepository<ProductNameEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<ProductNameDto, ProductNameEntity> resourceTransformer() {
        return mapper;
    }
}
