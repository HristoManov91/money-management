package com.example.money_management_be.controller.user;

import com.example.money_management_be.controller.crud.CrudController;
import com.example.money_management_be.dto.UserDto;
import com.example.money_management_be.entity.UserEntity;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/users")
@Tag(name = "Users", description = "Users operations")
public interface UserController extends CrudController<UserDto, UserEntity> {

    @Override
    ResponseEntity<Page<UserDto>> findAll(
        @RequestParam(value = "criteria") Optional<String> criteria,
        Predicate predicate,
        Pageable pageable);
}
