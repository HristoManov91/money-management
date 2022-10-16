package com.example.moneymanagementbe.mapper;

import com.example.moneymanagementbe.dto.ProductNameDto;
import com.example.moneymanagementbe.entity.ProductNameEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductNameMapper extends ResourceEntityTransformer<ProductNameDto, ProductNameEntity>{

    @Override
    ProductNameDto transformToResource(ProductNameEntity entity);

    @Override
    ProductNameEntity transformToEntity(ProductNameDto resource);
}
