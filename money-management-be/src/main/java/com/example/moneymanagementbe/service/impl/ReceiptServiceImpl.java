package com.example.moneymanagementbe.service.impl;

import com.example.moneymanagementbe.dto.ReceiptDto;
import com.example.moneymanagementbe.entity.ReceiptEntity;
import com.example.moneymanagementbe.mapper.ReceiptMapper;
import com.example.moneymanagementbe.mapper.ResourceEntityTransformer;
import com.example.moneymanagementbe.repository.BaseRepository;
import com.example.moneymanagementbe.repository.ReceiptRepository;
import com.example.moneymanagementbe.service.ReceiptService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReceiptServiceImpl implements ReceiptService {

    ReceiptRepository repository;
    ReceiptMapper mapper;

    @Override
    public BaseRepository<ReceiptEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<ReceiptDto, ReceiptEntity> resourceTransformer() {
        return mapper;
    }
}
