package com.example.moneymanagementbe.controller.receipt;

import com.example.moneymanagementbe.controller.SearchEnum;
import com.example.moneymanagementbe.controller.SearchStrategy;
import com.example.moneymanagementbe.dto.ReceiptDto;
import com.example.moneymanagementbe.entity.ReceiptEntity;
import com.example.moneymanagementbe.service.CrudService;
import com.example.moneymanagementbe.service.ReceiptService;
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
public class ReceiptControllerImpl implements ReceiptController {

    ReceiptService service;

    @Override
    public CrudService<ReceiptDto, ReceiptEntity> crudService() {
        return service;
    }

    @Override
    public ResponseEntity<Page<ReceiptDto>> findAll(
        Optional<String> criteria,
        @QuerydslPredicate(root = ReceiptEntity.class, bindings = ReceiptSearchStrategy.class) Predicate predicate,
        Pageable pageable) {

        SearchStrategy<?> searchStrategy = SearchEnum.RECEIPT.getSearchStrategy();
        return ResponseEntity.ok().body(service.findAll(searchStrategy.getCustomPredicate(predicate, criteria), pageable));
    }
}
