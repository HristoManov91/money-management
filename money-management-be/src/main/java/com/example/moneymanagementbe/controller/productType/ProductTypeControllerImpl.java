package com.example.moneymanagementbe.controller.productType;

import com.example.moneymanagementbe.controller.SearchEnum;
import com.example.moneymanagementbe.controller.SearchStrategy;
import com.example.moneymanagementbe.controller.product.ProductSearchStrategy;
import com.example.moneymanagementbe.dto.ProductTypeDto;
import com.example.moneymanagementbe.entity.ProductEntity;
import com.example.moneymanagementbe.entity.ProductTypeEntity;
import com.example.moneymanagementbe.service.CrudService;
import com.example.moneymanagementbe.service.ProductTypeService;
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
public class ProductTypeControllerImpl implements ProductTypeController {

    ProductTypeService service;

    @Override
    public CrudService<ProductTypeDto, ProductTypeEntity> crudService() {
        return service;
    }

    @Override
    public ResponseEntity<Page<ProductTypeDto>> findAll(
        Optional<String> criteria,
        @QuerydslPredicate(root = ProductEntity.class)Predicate predicate,
        Pageable pageable) {
        SearchStrategy<?> searchStrategy = SearchEnum.PRODUCT_TYPE.getSearchStrategy();
        return ResponseEntity.ok().body(service.findAll(searchStrategy.getCustomPredicate(predicate, criteria), pageable));
    }
}
