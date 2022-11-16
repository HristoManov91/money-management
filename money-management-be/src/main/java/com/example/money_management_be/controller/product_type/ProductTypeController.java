package com.example.money_management_be.controller.product_type;

import com.example.money_management_be.controller.crud.CrudController;
import com.example.money_management_be.dto.ProductTypeDto;
import com.example.money_management_be.entity.ProductTypeEntity;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/products/types")
@Tag(name = "ProductsTypes", description = "Products types operations")
public interface ProductTypeController extends CrudController<ProductTypeDto, ProductTypeEntity> {

    @Override
    ResponseEntity<Page<ProductTypeDto>> findAll(
        @RequestParam(value = "criteria") Optional<String> criteria,
        Predicate predicate,
        Pageable pageable);
}
