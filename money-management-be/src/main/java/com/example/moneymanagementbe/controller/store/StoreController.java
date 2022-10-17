package com.example.moneymanagementbe.controller.store;

import com.example.moneymanagementbe.controller.crud.CrudController;
import com.example.moneymanagementbe.dto.StoreDto;
import com.example.moneymanagementbe.entity.StoreEntity;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/stores")
@Tag(name = "Stores", description = "Stores operations")
public interface StoreController extends CrudController<StoreDto, StoreEntity> {

    @Override
    ResponseEntity<Page<StoreDto>> findAll(
        @RequestParam(value = "criteria")Optional<String> criteria,
        Predicate predicate,
        Pageable pageable);
}
