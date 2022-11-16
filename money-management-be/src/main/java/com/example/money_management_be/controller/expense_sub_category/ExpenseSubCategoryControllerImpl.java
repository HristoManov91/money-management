package com.example.money_management_be.controller.expense_sub_category;

import com.example.money_management_be.controller.SearchEnum;
import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.dto.ExpenseSubCategoryDto;
import com.example.money_management_be.entity.ExpenseCategoryEntity;
import com.example.money_management_be.entity.ExpenseSubCategoryEntity;
import com.example.money_management_be.service.CrudService;
import com.example.money_management_be.service.ExpenseSubCategoryService;
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
public class ExpenseSubCategoryControllerImpl implements ExpenseSubCategoryController {

    ExpenseSubCategoryService service;

    @Override
    public CrudService<ExpenseSubCategoryDto, ExpenseSubCategoryEntity> crudService() {
        return service;
    }

    @Override
    public ResponseEntity<Page<ExpenseSubCategoryDto>> findAll(
        Optional<String> criteria,
        @QuerydslPredicate(root = ExpenseSubCategoryEntity.class) Predicate predicate,
        Pageable pageable) {

        SearchStrategy<?> searchStrategy = SearchEnum.EXPENSE_SUB_CATEGORY.getSearchStrategy();
        return ResponseEntity.ok().body(service.findAll(searchStrategy.getCustomPredicate(predicate, criteria), pageable));
    }
}
