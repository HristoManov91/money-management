package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.ExpenseDto;
import com.example.money_management_be.entity.ExpenseEntity;
import com.example.money_management_be.mapper.qualifiers.ExpenseCategoryQualifier;
import com.example.money_management_be.mapper.qualifiers.ExpenseSubCategoryQualifier;
import com.example.money_management_be.mapper.qualifiers.UserQualifier;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true),
    uses = {
        ProductTypeMapper.class,
        ExpenseCategoryQualifier.class,
        ExpenseSubCategoryQualifier.class,
        UserQualifier.class})
public interface ExpenseMapper extends ResourceEntityTransformer<ExpenseDto, ExpenseEntity>{

    @Override
    @Mapping(target = "userId", source = "entity.user.id")
    @Mapping(target = "category", source = "entity.category.name")
    @Mapping(target = "subCategory", source = "entity.subCategory.name")
    ExpenseDto transformToResource(ExpenseEntity entity);

    @Override
    @Mapping(target = "user", source = "userId", qualifiedByName = "FromIdToUser")
    @Mapping(target = "category", source = "resource", qualifiedByName = "FromCategoryNameAndUserId")
    @Mapping(target = "subCategory", source = "resource", qualifiedByName = "FromSubCategoryNameAndCategoryId")
    ExpenseEntity transformToEntity(ExpenseDto resource);
}
