package com.example.money_management_be.controller.expense_category;

import com.example.money_management_be.controller.SearchEnum;
import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.dto.ExpenseCategoryDto;
import com.example.money_management_be.entity.ExpenseCategoryEntity;
import com.example.money_management_be.service.CrudService;
import com.example.money_management_be.service.ExpenseCategoryService;
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
public class ExpenseCategoryControllerImpl implements ExpenseCategoryController {

    ExpenseCategoryService service;

    @Override
    public CrudService<ExpenseCategoryDto, ExpenseCategoryEntity> crudService() {
        return service;
    }

    @Override
    public ResponseEntity<Page<ExpenseCategoryDto>> findAll(
        Optional<String> criteria,
        @QuerydslPredicate(root = ExpenseCategoryEntity.class) Predicate predicate,
        Pageable pageable) {

        SearchStrategy<?> searchStrategy = SearchEnum.EXPENSE_CATEGORY.getSearchStrategy();
        return ResponseEntity.ok().body(service.findAll(searchStrategy.getCustomPredicate(predicate, criteria), pageable));
    }
}
