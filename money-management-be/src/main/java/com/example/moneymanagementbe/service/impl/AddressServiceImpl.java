package com.example.moneymanagementbe.service.impl;

import com.example.moneymanagementbe.dto.AddressDto;
import com.example.moneymanagementbe.entity.AddressEntity;
import com.example.moneymanagementbe.mapper.AddressMapper;
import com.example.moneymanagementbe.mapper.ResourceEntityTransformer;
import com.example.moneymanagementbe.repository.AddressRepository;
import com.example.moneymanagementbe.repository.BaseRepository;
import com.example.moneymanagementbe.service.AddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressServiceImpl implements AddressService {

    AddressRepository repository;
    AddressMapper mapper;

    @Override
    public BaseRepository<AddressEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<AddressDto, AddressEntity> resourceTransformer() {
        return mapper;
    }
}
