package com.example.money_management_be.controller.expense;

import com.example.money_management_be.controller.crud.CrudController;
import com.example.money_management_be.dto.ExpenseDto;
import com.example.money_management_be.entity.ExpenseEntity;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/expenses")
@Tag(name = "Expenses", description = "Expenses operations")
public interface ExpenseController extends CrudController<ExpenseDto, ExpenseEntity> {

    @Override
    ResponseEntity<Page<ExpenseDto>> findAll(
        @RequestParam(value = "criteria") Optional<String> criteria,
        Predicate predicate,
        Pageable pageable);
}
