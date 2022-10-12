package com.example.moneymanagementbe.controller.user;

import com.example.moneymanagementbe.controller.crud.CrudController;
import com.example.moneymanagementbe.dto.UserDto;
import com.example.moneymanagementbe.entity.UserEntity;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/users")
public interface UserController extends CrudController<UserDto, UserEntity> {

    @Override
    ResponseEntity<Page<UserDto>> findAll(
        @RequestParam(value = "criteria")Optional<String> criteria,
        Predicate predicate,
        Pageable pageable);
}
