package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.ExpenseSubCategoryDto;
import com.example.money_management_be.entity.ExpenseSubCategoryEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {ExpenseCategoryMapper.class})
public interface ExpenseSubCategoryMapper extends ResourceEntityTransformer<ExpenseSubCategoryDto, ExpenseSubCategoryEntity>{

    @Override
    ExpenseSubCategoryDto transformToResource(ExpenseSubCategoryEntity entity);

    @Override
    ExpenseSubCategoryEntity transformToEntity(ExpenseSubCategoryDto resource);
}
