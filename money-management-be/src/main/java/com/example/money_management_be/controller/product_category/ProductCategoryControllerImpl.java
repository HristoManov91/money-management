package com.example.money_management_be.controller.product_category;

import com.example.money_management_be.controller.SearchEnum;
import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.dto.ProductCategoryDto;
import com.example.money_management_be.entity.ProductCategoryEntity;
import com.example.money_management_be.service.ProductCategoryService;
import com.example.money_management_be.service.CrudService;
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
public class ProductCategoryControllerImpl implements ProductCategoryController {

    ProductCategoryService service;

    @Override
    public CrudService<ProductCategoryDto, ProductCategoryEntity> crudService() {
        return service;
    }

    @Override
    public ResponseEntity<Page<ProductCategoryDto>> findAll(
        Optional<String> criteria,
        @QuerydslPredicate(root = ProductCategoryEntity.class)Predicate predicate,
        Pageable pageable) {

        SearchStrategy<?> searchStrategy = SearchEnum.PRODUCT_CATEGORY.getSearchStrategy();
        return ResponseEntity.ok().body(service.findAll(searchStrategy.getCustomPredicate(predicate, criteria), pageable));
    }
}
