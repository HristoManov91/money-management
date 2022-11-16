package com.example.money_management_be.service.impl;

import com.example.money_management_be.dto.StoreDto;
import com.example.money_management_be.entity.StoreEntity;
import com.example.money_management_be.mapper.ResourceEntityTransformer;
import com.example.money_management_be.mapper.StoreMapper;
import com.example.money_management_be.repository.BaseRepository;
import com.example.money_management_be.repository.StoreRepository;
import com.example.money_management_be.service.StoreService;
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
