package com.example.moneymanagementbe.controller;

import com.example.moneymanagementbe.dto.BaseDto;
import com.example.moneymanagementbe.entity.BaseEntity;
import com.example.moneymanagementbe.service.CrudService;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReadController<D extends BaseDto, E extends BaseEntity> {

    CrudService<D, E> crudService();

    //TODO swagger
    @GetMapping(produces = {"application/json"})
    default ResponseEntity<Page<D>> findAll(
        @RequestParam(value = "criteria") Optional<String> criteria,
        @QuerydslPredicate(root = BaseEntity.class) Predicate predicate,
        Pageable pageable) {
        return ResponseEntity.ok(crudService().findAll(predicate, pageable));
    }

    //TODO swagger
    @GetMapping(path = "{id}", produces = {"application/json"})
    default ResponseEntity<D> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(crudService().findById(id));
    }
}
