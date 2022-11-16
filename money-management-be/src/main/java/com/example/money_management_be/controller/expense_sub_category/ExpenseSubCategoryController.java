package com.example.money_management_be.controller.expense_sub_category;

import com.example.money_management_be.controller.crud.CrudController;
import com.example.money_management_be.dto.ExpenseSubCategoryDto;
import com.example.money_management_be.entity.ExpenseSubCategoryEntity;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/expense/sub-categories")
@Tag(name = "Expense sub categories", description = "Expense sub categories operations")
public interface ExpenseSubCategoryController extends CrudController<ExpenseSubCategoryDto, ExpenseSubCategoryEntity> {

    @Override
    ResponseEntity<Page<ExpenseSubCategoryDto>> findAll(
        @RequestParam(value = "criteria") Optional<String> criteria,
        Predicate predicate,
        Pageable pageable);
}
