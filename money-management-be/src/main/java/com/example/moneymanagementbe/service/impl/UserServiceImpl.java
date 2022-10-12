package com.example.moneymanagementbe.service.impl;

import com.example.moneymanagementbe.dto.UserDto;
import com.example.moneymanagementbe.entity.UserEntity;
import com.example.moneymanagementbe.mapper.ResourceEntityTransformer;
import com.example.moneymanagementbe.mapper.UserMapper;
import com.example.moneymanagementbe.repository.BaseRepository;
import com.example.moneymanagementbe.repository.UserRepository;
import com.example.moneymanagementbe.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository repository;
    UserMapper mapper;
    //TODO password encoder with security

    @Override
    public BaseRepository<UserEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<UserDto, UserEntity> resourceTransformer() {
        return mapper;
    }
}
