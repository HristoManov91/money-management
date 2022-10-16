package com.example.moneymanagementbe.mapper;

import com.example.moneymanagementbe.dto.ReceiptDto;
import com.example.moneymanagementbe.entity.ReceiptEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ReceiptMapper extends ResourceEntityTransformer<ReceiptDto, ReceiptEntity> {

    @Override
    ReceiptDto transformToResource(ReceiptEntity entity);

    @Override
    ReceiptEntity transformToEntity(ReceiptDto resource);
}
