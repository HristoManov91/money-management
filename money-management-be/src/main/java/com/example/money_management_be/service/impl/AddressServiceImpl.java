package com.example.money_management_be.service.impl;

import com.example.money_management_be.dto.AddressDto;
import com.example.money_management_be.entity.AddressEntity;
import com.example.money_management_be.mapper.AddressMapper;
import com.example.money_management_be.mapper.ResourceEntityTransformer;
import com.example.money_management_be.repository.AddressRepository;
import com.example.money_management_be.repository.BaseRepository;
import com.example.money_management_be.service.AddressService;
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
