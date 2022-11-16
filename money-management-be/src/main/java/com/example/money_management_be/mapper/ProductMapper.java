package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.ProductDto;
import com.example.money_management_be.entity.ProductEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {ProductTypeMapper.class})
public interface ProductMapper extends ResourceEntityTransformer<ProductDto, ProductEntity>{

    @Override
    ProductDto transformToResource(ProductEntity entity);

    @Override
    ProductEntity transformToEntity(ProductDto resource);
}
