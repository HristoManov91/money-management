package com.example.moneymanagementbe.controller.crud;

import com.example.moneymanagementbe.dto.BaseDto;
import com.example.moneymanagementbe.entity.BaseEntity;
import com.example.moneymanagementbe.service.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface EditController<D extends BaseDto, E extends BaseEntity> {

    CrudService<D, E> crudService();

    @Operation(
        summary = "Update resource by Id value.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Resource edited successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource with this id was not found", content = @Content)
        })
    @PutMapping(path = "{id}", produces = {"application/json"})
    default ResponseEntity<D> edit(@PathVariable(name = "id") Long id, @Valid @RequestBody D dto) {
        // TODO : check id validate
        return ResponseEntity.ok(crudService().saveAndFlush(dto));
    }
}
