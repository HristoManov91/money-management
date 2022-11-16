package com.example.money_management_be.service.impl;

import com.example.money_management_be.dto.ExpenseSubCategoryDto;
import com.example.money_management_be.entity.ExpenseSubCategoryEntity;
import com.example.money_management_be.mapper.ExpenseSubCategoryMapper;
import com.example.money_management_be.mapper.ResourceEntityTransformer;
import com.example.money_management_be.repository.BaseRepository;
import com.example.money_management_be.repository.ExpenseSubCategoryRepository;
import com.example.money_management_be.service.ExpenseSubCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExpenseSubCategoryServiceImpl implements ExpenseSubCategoryService {

    ExpenseSubCategoryRepository repository;
    ExpenseSubCategoryMapper mapper;

    @Override
    public BaseRepository<ExpenseSubCategoryEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<ExpenseSubCategoryDto, ExpenseSubCategoryEntity> resourceTransformer() {
        return mapper;
    }
}
