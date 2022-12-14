package com.example.money_management_be.controller.product;

import com.example.money_management_be.controller.crud.CrudController;
import com.example.money_management_be.dto.ProductDto;
import com.example.money_management_be.entity.ProductEntity;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/products")
@Tag(name = "Products", description = "Products operations")
public interface ProductController extends CrudController<ProductDto, ProductEntity> {

    @Override
    ResponseEntity<Page<ProductDto>> findAll(
        @RequestParam(value = "criteria") Optional<String> criteria,
        Predicate predicate,
        Pageable pageable);
}
