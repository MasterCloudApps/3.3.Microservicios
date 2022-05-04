package es.codeurjc.employees.controllers;

import es.codeurjc.employees.dtos.requests.EmployeeRequestDto;
import es.codeurjc.employees.dtos.responses.EmployeeResponseDto;
import es.codeurjc.employees.services.EmployeeService;
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
@RequestMapping("/api/v1/employ")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all employees",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EmployeeResponseDto.class)))})})
    @GetMapping("/")
    public Collection<EmployeeResponseDto> getEmployees() {
        return this.employeeService.findAll();
    }

    @Operation(summary = "Get a employee by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "employee not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployee(@Parameter(description = "id of employee to be searched")
                                   @PathVariable long id) {
        return this.employeeService.findById(id);
    }

    @Operation(summary = "Create a new employee")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Employee to be created", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeRequestDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid employee attributes supplied",
                    content = @Content)})
    @PostMapping("/")
    public EmployeeResponseDto createEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        return this.employeeService.save(employeeRequestDto);
    }

    @Operation(summary = "Deletes employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "employee deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "employee not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public EmployeeResponseDto deleteEmployee(@Parameter(description = "id of employee to be deleted") @PathVariable long id) {
        return this.employeeService.delete(id);
    }

}
