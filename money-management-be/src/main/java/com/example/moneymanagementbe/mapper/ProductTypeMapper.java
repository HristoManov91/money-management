package com.example.moneymanagementbe.mapper;

import com.example.moneymanagementbe.dto.ProductTypeDto;
import com.example.moneymanagementbe.entity.ProductTypeEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductTypeMapper extends ResourceEntityTransformer<ProductTypeDto, ProductTypeEntity>{

    @Override
    ProductTypeDto transformToResource(ProductTypeEntity entity);

    @Override
    ProductTypeEntity transformToEntity(ProductTypeDto resource);
}
