package com.example.money_management_be.service.impl;

import com.example.money_management_be.dto.ProductCategoryDto;
import com.example.money_management_be.entity.ProductCategoryEntity;
import com.example.money_management_be.mapper.ProductCategoryMapper;
import com.example.money_management_be.mapper.ResourceEntityTransformer;
import com.example.money_management_be.repository.BaseRepository;
import com.example.money_management_be.repository.ProductCategoryRepository;
import com.example.money_management_be.service.ProductCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductCategoryServiceImpl implements ProductCategoryService {

    ProductCategoryRepository repository;
    ProductCategoryMapper mapper;

    @Override
    public BaseRepository<ProductCategoryEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<ProductCategoryDto, ProductCategoryEntity> resourceTransformer() {
        return mapper;
    }

    //TODO cron job for delete duplicate categories
}
