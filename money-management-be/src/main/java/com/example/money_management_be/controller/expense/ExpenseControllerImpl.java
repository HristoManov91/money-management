package com.example.money_management_be.controller.expense;

import com.example.money_management_be.controller.SearchEnum;
import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.dto.ExpenseDto;
import com.example.money_management_be.entity.ExpenseEntity;
import com.example.money_management_be.service.CrudService;
import com.example.money_management_be.service.ExpenseService;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "http://localhost:8080/")
@RequiredArgsConstructor
public class ExpenseControllerImpl implements ExpenseController {

    ExpenseService service;

    @Override
    public CrudService<ExpenseDto, ExpenseEntity> crudService() {
        return service;
    }

    @Override
    public ResponseEntity<Page<ExpenseDto>> findAll(
        Optional<String> criteria,
        @QuerydslPredicate(root = ExpenseEntity.class, bindings = ExpenseSearchStrategy.class) Predicate predicate,
        Pageable pageable) {

        SearchStrategy<?> searchStrategy = SearchEnum.EXPENSE.getSearchStrategy();
        return ResponseEntity.ok().body(service.findAll(searchStrategy.getCustomPredicate(predicate, criteria), pageable));
    }

    @Override
    public ResponseEntity<ExpenseDto> save(ExpenseDto dto) {
        //TODO get from session
        dto.setUserId(1L);
        ExpenseDto save = service.save(dto);
        //TODO to make it not return the object
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
}
