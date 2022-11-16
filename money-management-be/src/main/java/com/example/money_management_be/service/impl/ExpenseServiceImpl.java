package com.example.money_management_be.service.impl;

import com.example.money_management_be.dto.ExpenseDto;
import com.example.money_management_be.entity.ExpenseEntity;
import com.example.money_management_be.mapper.ExpenseMapper;
import com.example.money_management_be.mapper.ResourceEntityTransformer;
import com.example.money_management_be.repository.BaseRepository;
import com.example.money_management_be.repository.ExpenseRepository;
import com.example.money_management_be.service.ExpenseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExpenseServiceImpl implements ExpenseService {

    ExpenseRepository repository;
    ExpenseMapper mapper;

    @Override
    public BaseRepository<ExpenseEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<ExpenseDto, ExpenseEntity> resourceTransformer() {
        return mapper;
    }
}
