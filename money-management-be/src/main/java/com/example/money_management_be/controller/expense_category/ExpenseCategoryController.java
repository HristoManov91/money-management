package com.example.money_management_be.controller.expense_category;

import com.example.money_management_be.controller.crud.CrudController;
import com.example.money_management_be.dto.ExpenseCategoryDto;
import com.example.money_management_be.entity.ExpenseCategoryEntity;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/expense/categories")
@Tag(name = "Expense categories", description = "Expense categories operations")
public interface ExpenseCategoryController extends CrudController<ExpenseCategoryDto, ExpenseCategoryEntity> {

    @Override
    ResponseEntity<Page<ExpenseCategoryDto>> findAll(
        @RequestParam(value = "criteria") Optional<String> criteria,
        Predicate predicate,
        Pageable pageable);
}
