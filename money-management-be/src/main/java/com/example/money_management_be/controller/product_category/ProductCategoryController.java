package com.example.money_management_be.controller.product_category;

import com.example.money_management_be.controller.crud.CrudController;
import com.example.money_management_be.dto.ProductCategoryDto;
import com.example.money_management_be.entity.ProductCategoryEntity;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/products/categories")
@Tag(name = "ProductsCategories", description = "Products categories operations")
public interface ProductCategoryController extends CrudController<ProductCategoryDto, ProductCategoryEntity> {

    @Override
    ResponseEntity<Page<ProductCategoryDto>> findAll(
        @RequestParam(value = "criteria") Optional<String> criteria,
        Predicate predicate,
        Pageable pageable);
}
