package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.ProductTypeDto;
import com.example.money_management_be.entity.ProductTypeEntity;
import com.example.money_management_be.mapper.qualifiers.ProductCategoryQualifier;
import com.example.money_management_be.mapper.qualifiers.UserQualifier;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {UserQualifier.class, ProductCategoryQualifier.class})
public interface ProductTypeMapper extends ResourceEntityTransformer<ProductTypeDto, ProductTypeEntity> {

    @Override
    @Mapping(target = "userId", source = "entity.user.id")
    @Mapping(target = "category", source = "entity.category.name")
    ProductTypeDto transformToResource(ProductTypeEntity entity);

    @Override
    @Mapping(target = "user", source = "userId", qualifiedByName = "FromIdToUser")
    @Mapping(target = "category", source = "resource", qualifiedByName = "FromNameAndUserId")
    ProductTypeEntity transformToEntity(ProductTypeDto resource);
}
