package com.example.moneymanagementbe.mapper;

import com.example.moneymanagementbe.dto.UserDto;
import com.example.moneymanagementbe.entity.UserEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper extends ResourceEntityTransformer<UserDto , UserEntity>{

    @Override
    UserDto transformToResource (UserEntity entity);

    @Override
    UserEntity transformToEntity (UserDto resource);
}
