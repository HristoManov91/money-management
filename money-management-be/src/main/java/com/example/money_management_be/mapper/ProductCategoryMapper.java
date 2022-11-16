package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.ProductCategoryDto;
import com.example.money_management_be.entity.ProductCategoryEntity;
import com.example.money_management_be.mapper.qualifiers.UserQualifier;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {UserQualifier.class})
public interface ProductCategoryMapper extends ResourceEntityTransformer<ProductCategoryDto, ProductCategoryEntity> {

    @Override
    @Mapping(target = "userId", source = "entity.user.id")
    ProductCategoryDto transformToResource(ProductCategoryEntity entity);

    @Override
    @Mapping(target = "user", source = "userId", qualifiedByName = "FromIdToUser")
    ProductCategoryEntity transformToEntity(ProductCategoryDto resource);
}
