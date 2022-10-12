package com.example.moneymanagementbe.controller.user;

import com.example.moneymanagementbe.controller.SearchEnum;
import com.example.moneymanagementbe.controller.SearchStrategy;
import com.example.moneymanagementbe.dto.UserDto;
import com.example.moneymanagementbe.entity.UserEntity;
import com.example.moneymanagementbe.service.CrudService;
import com.example.moneymanagementbe.service.UserService;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    UserService service;

    @Override
    public CrudService<UserDto, UserEntity> crudService() {
        return service;
    }

    @Override
    public ResponseEntity<Page<UserDto>> findAll(
        Optional<String> criteria,
        @QuerydslPredicate(root = UserEntity.class, bindings = UserSearchStrategy.class) Predicate predicate,
        Pageable pageable) {

        SearchStrategy<?> searchStrategy = SearchEnum.USER.getSearchStrategy();
        return ResponseEntity.ok().body(service.findAll(searchStrategy.getCustomPredicate(predicate, criteria), pageable));
    }
}
