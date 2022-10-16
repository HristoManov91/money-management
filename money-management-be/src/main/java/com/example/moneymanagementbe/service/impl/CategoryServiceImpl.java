package com.example.moneymanagementbe.service.impl;

import com.example.moneymanagementbe.dto.CategoryDto;
import com.example.moneymanagementbe.entity.CategoryEntity;
import com.example.moneymanagementbe.mapper.CategoryMapper;
import com.example.moneymanagementbe.mapper.ResourceEntityTransformer;
import com.example.moneymanagementbe.repository.BaseRepository;
import com.example.moneymanagementbe.repository.CategoryRepository;
import com.example.moneymanagementbe.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository repository;
    CategoryMapper mapper;

    @Override
    public BaseRepository<CategoryEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<CategoryDto, CategoryEntity> resourceTransformer() {
        return mapper;
    }
}
