package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.UserDto;
import com.example.money_management_be.entity.UserEntity;
import com.example.money_management_be.mapper.qualifiers.UserQualifier;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {UserQualifier.class})
public interface UserMapper extends ResourceEntityTransformer<UserDto , UserEntity>{

    @Override
    @Mapping(target = "roles", source = "roles", qualifiedByName = "MapRolesFromEntity")
    UserDto transformToResource (UserEntity entity);

    @Override
    @Mapping(target = "roles", source = "roles", qualifiedByName = "MapRolesFromString")
    UserEntity transformToEntity (UserDto resource);
}
