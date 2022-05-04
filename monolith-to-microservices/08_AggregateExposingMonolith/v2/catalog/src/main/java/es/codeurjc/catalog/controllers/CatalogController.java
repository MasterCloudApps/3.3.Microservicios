package es.codeurjc.catalog.controllers;

import es.codeurjc.catalog.dtos.requests.EmployeeRequestDto;
import es.codeurjc.catalog.dtos.responses.EmployeeResponseDto;
import es.codeurjc.catalog.services.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    private CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Operation(summary = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all employees",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EmployeeResponseDto.class)))})})
    @GetMapping("/")
    public Collection<EmployeeResponseDto> getEmployees() {
        return this.catalogService.findAll();
    }

    @Operation(summary = "Get a employee by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content)})
    @GetMapping("/{employeeId}")
    public EmployeeResponseDto getEmployee(@Parameter(description = "id of employee to be searched")
    @PathVariable String employeeId) {
            return this.catalogService.findById(Long.parseLong(employeeId));
    }

    @Operation(summary = "Create a new employee")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order to be created", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeRequestDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid employ attributes supplied",
                    content = @Content)})
    @PostMapping("/")
    public EmployeeResponseDto createOrder(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        return this.catalogService.save(employeeRequestDto);
    }

    @Operation(summary = "Deletes employ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employ deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public EmployeeResponseDto deleteUser(@Parameter(description = "id of employ to be deleted") @PathVariable long id) {
        return this.catalogService.delete(id);
    }
}
