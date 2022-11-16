package com.example.money_management_be.mapper;

import com.example.money_management_be.dto.AddressDto;
import com.example.money_management_be.entity.AddressEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AddressMapper extends ResourceEntityTransformer<AddressDto, AddressEntity>{

    @Override
    AddressDto transformToResource(AddressEntity entity);

    @Override
    AddressEntity transformToEntity(AddressDto resource);
}
