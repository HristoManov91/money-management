package com.example.money_management_be.service.impl;

import com.example.money_management_be.dto.ExpenseCategoryDto;
import com.example.money_management_be.dto.ExpenseDto;
import com.example.money_management_be.entity.ExpenseCategoryEntity;
import com.example.money_management_be.entity.ExpenseEntity;
import com.example.money_management_be.mapper.ExpenseCategoryMapper;
import com.example.money_management_be.mapper.ExpenseMapper;
import com.example.money_management_be.mapper.ResourceEntityTransformer;
import com.example.money_management_be.repository.BaseRepository;
import com.example.money_management_be.repository.ExpenseCategoryRepository;
import com.example.money_management_be.repository.ExpenseRepository;
import com.example.money_management_be.service.ExpenseCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    ExpenseCategoryRepository repository;
    ExpenseCategoryMapper mapper;

    @Override
    public BaseRepository<ExpenseCategoryEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<ExpenseCategoryDto, ExpenseCategoryEntity> resourceTransformer() {
        return mapper;
    }
}
