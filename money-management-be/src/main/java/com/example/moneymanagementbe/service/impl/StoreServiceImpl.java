package com.example.moneymanagementbe.service.impl;

import com.example.moneymanagementbe.dto.StoreDto;
import com.example.moneymanagementbe.entity.StoreEntity;
import com.example.moneymanagementbe.mapper.ResourceEntityTransformer;
import com.example.moneymanagementbe.mapper.StoreMapper;
import com.example.moneymanagementbe.repository.BaseRepository;
import com.example.moneymanagementbe.repository.StoreRepository;
import com.example.moneymanagementbe.service.StoreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StoreServiceImpl implements StoreService {

    StoreRepository repository;
    StoreMapper mapper;

    @Override
    public BaseRepository<StoreEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<StoreDto, StoreEntity> resourceTransformer() {
        return mapper;
    }
}
