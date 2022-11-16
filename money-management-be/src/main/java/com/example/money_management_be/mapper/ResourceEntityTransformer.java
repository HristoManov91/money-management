package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.BaseDto;
import com.example.money_management_be.entity.BaseEntity;

public interface ResourceEntityTransformer<R extends BaseDto, E extends BaseEntity> {

    R transformToResource(E entity);

    E transformToEntity(R resource);
}
