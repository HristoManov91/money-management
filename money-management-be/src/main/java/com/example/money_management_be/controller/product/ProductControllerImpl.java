package com.example.money_management_be.controller.product;

import com.example.money_management_be.controller.SearchEnum;
import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.dto.ProductDto;
import com.example.money_management_be.entity.ProductEntity;
import com.example.money_management_be.service.CrudService;
import com.example.money_management_be.service.ProductService;
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
public class ProductControllerImpl implements ProductController {

    ProductService service;

    @Override
    public CrudService<ProductDto, ProductEntity> crudService() {
        return service;
    }

    @Override
    public ResponseEntity<Page<ProductDto>> findAll(
        Optional<String> criteria,
        @QuerydslPredicate(root = ProductEntity.class, bindings = ProductSearchStrategy.class) Predicate predicate,
        Pageable pageable) {
        SearchStrategy<?> searchStrategy = SearchEnum.PRODUCT.getSearchStrategy();
        return ResponseEntity.ok().body(service.findAll(searchStrategy.getCustomPredicate(predicate, criteria), pageable));
    }
}
