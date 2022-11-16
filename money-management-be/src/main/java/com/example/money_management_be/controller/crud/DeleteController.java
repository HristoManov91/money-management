package com.example.money_management_be.controller.crud;

import com.example.money_management_be.dto.BaseDto;
import com.example.money_management_be.entity.BaseEntity;
import com.example.money_management_be.service.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface DeleteController<D extends BaseDto, E extends BaseEntity> {

    CrudService<D, E> crudService();


    @Operation(
        summary = "Delete resource by Id value.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Resource delete successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource with this id was not found", content = @Content)
        })
    @Parameter(name = "id", description = "The ID of the resource", required = true)
    @DeleteMapping(path = "{id}")
    default ResponseEntity<D> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(crudService().deleteById(id));
    }

    @Operation(summary = "Delete resources by list of id value.")
    @Parameter(name = "ids", description = "The IDs of the resources", required = true)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All resources deleted.", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Resource with this id was not found.", content = @Content)
    })
    default ResponseEntity<Void> deleteAll(@RequestBody List<Long> idList) {
        crudService().deleteAll(idList);
        return ResponseEntity.noContent().build();
    }
}
