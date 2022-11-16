package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.ExpenseCategoryDto;
import com.example.money_management_be.entity.ExpenseCategoryEntity;
import com.example.money_management_be.mapper.qualifiers.UserQualifier;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {UserQualifier.class})
public interface ExpenseCategoryMapper extends ResourceEntityTransformer<ExpenseCategoryDto, ExpenseCategoryEntity>{

    @Override
    @Mapping(target = "userId", source = "entity.user.id")
    ExpenseCategoryDto transformToResource(ExpenseCategoryEntity entity);

    @Override
    @Mapping(target = "user", source = "userId", qualifiedByName = "FromIdToUser")
    ExpenseCategoryEntity transformToEntity(ExpenseCategoryDto resource);
}
