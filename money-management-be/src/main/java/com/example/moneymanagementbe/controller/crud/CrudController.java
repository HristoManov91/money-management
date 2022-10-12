package com.example.moneymanagementbe.controller.crud;

import com.example.moneymanagementbe.dto.BaseDto;
import com.example.moneymanagementbe.entity.BaseEntity;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface CrudController<D extends BaseDto, E extends BaseEntity> extends ReadController<D, E>, EditController<D, E>, DeleteController<D, E> {

    //TODO swagger
    @PostMapping(consumes = {"application/json"})
    default ResponseEntity<D> save(@Valid @RequestBody D dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crudService().save(dto));
    }
}
