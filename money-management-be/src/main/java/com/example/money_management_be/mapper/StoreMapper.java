package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.StoreDto;
import com.example.money_management_be.entity.StoreEntity;
import com.example.money_management_be.mapper.qualifiers.UserQualifier;
import com.example.money_management_be.service.UserService;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {UserQualifier.class})
public interface StoreMapper extends ResourceEntityTransformer<StoreDto, StoreEntity> {

    @Override
    @Mapping(target = "userId", source = "entity.user.id")
    StoreDto transformToResource(StoreEntity entity);

    @Override
    @Mapping(target = "user", source = "userId", qualifiedByName = "FromIdToUser")
    StoreEntity transformToEntity(StoreDto resource);
}
