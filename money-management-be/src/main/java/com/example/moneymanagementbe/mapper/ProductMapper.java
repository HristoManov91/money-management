package com.example.moneymanagementbe.mapper;

import com.example.moneymanagementbe.dto.ProductDto;
import com.example.moneymanagementbe.entity.ProductEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductMapper extends ResourceEntityTransformer<ProductDto, ProductEntity>{

    @Override
    ProductDto transformToResource(ProductEntity entity);

    @Override
    ProductEntity transformToEntity(ProductDto resource);
}
