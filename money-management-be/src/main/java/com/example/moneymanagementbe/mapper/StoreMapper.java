package com.example.moneymanagementbe.mapper;

import com.example.moneymanagementbe.dto.StoreDto;
import com.example.moneymanagementbe.entity.StoreEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface StoreMapper extends ResourceEntityTransformer<StoreDto, StoreEntity> {

    @Override
    StoreDto transformToResource(StoreEntity entity);

    @Override
    StoreEntity transformToEntity(StoreDto resource);
}
