package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.ProductTypeDto;
import com.example.money_management_be.entity.ProductTypeEntity;
import com.example.money_management_be.mapper.qualifiers.UserQualifier;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {UserQualifier.class, ProductCategoryMapper.class})
public interface ProductTypeMapper extends ResourceEntityTransformer<ProductTypeDto, ProductTypeEntity> {

    @Override
    @Mapping(target = "userId", source = "entity.user.id")
    ProductTypeDto transformToResource(ProductTypeEntity entity);

    @Override
    @Mapping(target = "user", source = "userId", qualifiedByName = "FromIdToUser")
    ProductTypeEntity transformToEntity(ProductTypeDto resource);
}
