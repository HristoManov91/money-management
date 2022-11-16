package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.ExpenseDto;
import com.example.money_management_be.entity.ExpenseEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true),
        uses = {StoreMapper.class, ProductTypeMapper.class, ExpenseCategoryMapper.class})
public interface ExpenseMapper extends ResourceEntityTransformer<ExpenseDto, ExpenseEntity>{

    @Override
    ExpenseDto transformToResource(ExpenseEntity entity);

    @Override
    ExpenseEntity transformToEntity(ExpenseDto resource);
}
