package com.example.moneymanagementbe.mapper;

import com.example.moneymanagementbe.dto.AddressDto;
import com.example.moneymanagementbe.entity.AddressEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AddressMapper extends ResourceEntityTransformer<AddressDto, AddressEntity>{

    @Override
    AddressDto transformToResource(AddressEntity entity);

    @Override
    AddressEntity transformToEntity(AddressDto resource);
}
