package com.example.moneymanagementbe.controller.crud;

import com.example.moneymanagementbe.dto.BaseDto;
import com.example.moneymanagementbe.entity.BaseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface CrudController<D extends BaseDto, E extends BaseEntity> extends ReadController<D, E>, EditController<D, E>, DeleteController<D, E> {

    @Operation(summary = "Create a new Object", description = "Create a new Object from DTO object")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Object Created"),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    })
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    default ResponseEntity<D> save(@Valid @RequestBody D dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crudService().save(dto));
    }
}
