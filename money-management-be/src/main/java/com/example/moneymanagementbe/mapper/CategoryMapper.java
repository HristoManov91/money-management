package com.example.moneymanagementbe.mapper;

import com.example.moneymanagementbe.dto.CategoryDto;
import com.example.moneymanagementbe.entity.CategoryEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CategoryMapper extends ResourceEntityTransformer<CategoryDto, CategoryEntity> {

    @Override
    CategoryDto transformToResource(CategoryEntity entity);

    @Override
    CategoryEntity transformToEntity(CategoryDto resource);
}
