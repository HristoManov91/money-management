package com.example.money_management_be.service.impl;

import com.example.money_management_be.dto.UserDto;
import com.example.money_management_be.entity.UserEntity;
import com.example.money_management_be.entity.UserRoleEntity;
import com.example.money_management_be.entity.enums.GenderEnum;
import com.example.money_management_be.entity.enums.UserRole;
import com.example.money_management_be.mapper.ResourceEntityTransformer;
import com.example.money_management_be.mapper.UserMapper;
import com.example.money_management_be.repository.BaseRepository;
import com.example.money_management_be.repository.UserRepository;
import com.example.money_management_be.repository.UserRoleRepository;
import com.example.money_management_be.service.UserService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository repository;
    UserRoleRepository roleRepository;
    UserMapper mapper;
    PasswordEncoder passwordEncoder;

    @Override
    public BaseRepository<UserEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<UserDto, UserEntity> resourceTransformer() {
        return mapper;
    }

    @Override
    public boolean registerUser(UserDto userDto) {
        UserEntity userEntity = mapper.transformToEntity(userDto);
        UserEntity user =
            UserEntity.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .dateOfBirth(userDto.getDateOfBirth())
                .fullName(userDto.getFullName())
                .gender(GenderEnum.valueOf(userDto.getGender()))
                .roles(List.of(roleRepository.findByRole(UserRole.USER).get()))
                .build();

        repository.save(user);

        log.info("Register user successfully.");

        return true;
    }

    @Override
    public void initUserRoles() {
        if (roleRepository.count() == 0){
            UserRoleEntity admin = new UserRoleEntity(UserRole.ADMIN);
            roleRepository.save(admin);

            UserRoleEntity user = new UserRoleEntity(UserRole.USER);
            roleRepository.save(user);
        }
    }
}
