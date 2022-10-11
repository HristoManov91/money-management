package com.example.moneymanagementbe.mapper;

import com.example.moneymanagementbe.dto.BaseDto;
import com.example.moneymanagementbe.entity.BaseEntity;

public interface ResourceEntityTransformer<R extends BaseDto, E extends BaseEntity> {

    R transformToResource(E entity);

    E transformToEntity(R resource);
}
