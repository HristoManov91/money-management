package com.example.moneymanagementbe.controller.receipt;

import com.example.moneymanagementbe.controller.crud.CrudController;
import com.example.moneymanagementbe.dto.ReceiptDto;
import com.example.moneymanagementbe.entity.ReceiptEntity;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/receipts")
@Tag(name = "Receipts", description = "Receipts operations")
public interface ReceiptController extends CrudController<ReceiptDto, ReceiptEntity> {

    @Override
    ResponseEntity<Page<ReceiptDto>> findAll(
        @RequestParam(value = "criteria") Optional<String> criteria,
        Predicate predicate,
        Pageable pageable);
}
