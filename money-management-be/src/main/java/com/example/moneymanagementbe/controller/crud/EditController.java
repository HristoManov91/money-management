package com.example.moneymanagementbe.controller.crud;

import com.example.moneymanagementbe.dto.BaseDto;
import com.example.moneymanagementbe.entity.BaseEntity;
import com.example.moneymanagementbe.service.CrudService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface EditController<D extends BaseDto, E extends BaseEntity> {

    CrudService<D, E> crudService();

    @PutMapping(path = "{id}", produces = {"application/json"})
    default ResponseEntity<D> edit(@PathVariable(name = "id") Long id, @Valid @RequestBody D dto) {
        // TODO : check id validate
        return ResponseEntity.ok(crudService().saveAndFlush(dto));
    }
}
