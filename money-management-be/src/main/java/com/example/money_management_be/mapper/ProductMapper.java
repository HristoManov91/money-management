package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.ProductDto;
import com.example.money_management_be.entity.ProductEntity;
import com.example.money_management_be.mapper.qualifiers.ProductTypeQualifier;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {ProductTypeMapper.class, ProductTypeQualifier.class})
public interface ProductMapper extends ResourceEntityTransformer<ProductDto, ProductEntity>{

    @Override
    ProductDto transformToResource(ProductEntity entity);

    @Override
    @Mapping(target = "productType", source = "resource.productType", qualifiedByName = "FindProductType")
    ProductEntity transformToEntity(ProductDto resource);
}
