package com.example.moneymanagementbe.controller;

import com.example.moneymanagementbe.dto.BaseDto;
import com.example.moneymanagementbe.entity.BaseEntity;
import com.example.moneymanagementbe.service.CrudService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface DeleteController<D extends BaseDto, E extends BaseEntity> {

    CrudService<D, E> crudService();

    //TODO swagger
    @DeleteMapping(path = "{id}")
    default ResponseEntity<D> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(crudService().deleteById(id));
    }

    //TODO swagger
    default ResponseEntity<Void> deleteAll(@RequestBody List<Long> idList) {
        crudService().deleteAll(idList);
        return ResponseEntity.noContent().build();
    }
}
