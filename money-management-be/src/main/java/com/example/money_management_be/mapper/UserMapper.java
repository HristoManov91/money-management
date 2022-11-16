package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.UserDto;
import com.example.money_management_be.entity.UserEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper extends ResourceEntityTransformer<UserDto , UserEntity>{

    @Override
    UserDto transformToResource (UserEntity entity);

    @Override
    UserEntity transformToEntity (UserDto resource);
}
