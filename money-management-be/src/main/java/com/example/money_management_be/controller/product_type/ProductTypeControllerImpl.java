package com.example.money_management_be.controller.product_type;

import com.example.money_management_be.controller.SearchEnum;
import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.dto.ProductTypeDto;
import com.example.money_management_be.entity.ProductTypeEntity;
import com.example.money_management_be.service.CrudService;
import com.example.money_management_be.service.ProductTypeService;
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
        @QuerydslPredicate(root = ProductTypeEntity.class)Predicate predicate,
        Pageable pageable) {
        SearchStrategy<?> searchStrategy = SearchEnum.PRODUCT_TYPE.getSearchStrategy();
        return ResponseEntity.ok().body(service.findAll(searchStrategy.getCustomPredicate(predicate, criteria), pageable));
    }
}
