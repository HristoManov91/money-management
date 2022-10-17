package com.example.moneymanagementbe.controller.store;

import com.example.moneymanagementbe.controller.SearchEnum;
import com.example.moneymanagementbe.controller.SearchStrategy;
import com.example.moneymanagementbe.dto.StoreDto;
import com.example.moneymanagementbe.entity.StoreEntity;
import com.example.moneymanagementbe.service.CrudService;
import com.example.moneymanagementbe.service.StoreService;
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
public class StoreControllerImpl implements StoreController {

    StoreService service;

    @Override
    public CrudService<StoreDto, StoreEntity> crudService() {
        return service;
    }

    @Override
    public ResponseEntity<Page<StoreDto>> findAll(
        Optional<String> criteria,
        @QuerydslPredicate(root = StoreEntity.class, bindings = StoreSearchStrategy.class) Predicate predicate,
        Pageable pageable) {

        SearchStrategy<?> searchStrategy = SearchEnum.STORE.getSearchStrategy();
        return ResponseEntity.ok().body(service.findAll(searchStrategy.getCustomPredicate(predicate, criteria), pageable));
    }
}
