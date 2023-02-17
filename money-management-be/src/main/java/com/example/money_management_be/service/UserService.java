package com.example.money_management_be.service;

import com.example.money_management_be.dto.UserDto;
import com.example.money_management_be.entity.UserEntity;

public interface UserService extends CrudService<UserDto, UserEntity> {

    /**
     * Register User from userDto.
     * @param userDto This is the user dto object with all data.
     * @return Boolean value.
     */
    boolean registerUser(UserDto userDto);

    void initUserRoles();
}
