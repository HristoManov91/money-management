package com.example.money_management_be.service.impl;

import com.example.money_management_be.dto.UserDto;
import com.example.money_management_be.entity.UserEntity;
import com.example.money_management_be.mapper.ResourceEntityTransformer;
import com.example.money_management_be.mapper.UserMapper;
import com.example.money_management_be.repository.BaseRepository;
import com.example.money_management_be.repository.UserRepository;
import com.example.money_management_be.service.UserService;
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
