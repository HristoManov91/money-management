package com.example.moneymanagementbe.controller.crud;

import com.example.moneymanagementbe.dto.BaseDto;
import com.example.moneymanagementbe.entity.BaseEntity;
import com.example.moneymanagementbe.service.CrudService;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Find all resources by ids")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Return all resources fount."),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    })
    @GetMapping( produces = {"application/json"})
    default ResponseEntity<Page<D>> findAll(
        @RequestParam(value = "criteria") Optional<String> criteria,
        @QuerydslPredicate(root = BaseEntity.class) Predicate predicate,
        Pageable pageable) {
        return ResponseEntity.ok(crudService().findAll(predicate, pageable));
    }

    @Operation(summary = "Find resource by id.")
    @Parameter(name = "id", description = "The ID from type Long of the resource", required = true)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the resource"),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Resource with this id was not found", content = @Content)// TODO content class exception
    })
    @GetMapping(path = "{id}", produces = {"application/json"})
    default ResponseEntity<D> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(crudService().findById(id));
    }
}
