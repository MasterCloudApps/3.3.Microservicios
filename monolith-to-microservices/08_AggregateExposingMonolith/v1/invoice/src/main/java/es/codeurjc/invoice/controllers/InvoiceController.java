package es.codeurjc.invoice.controllers;

import es.codeurjc.invoice.dtos.requests.EmployeeRequestDto;
import es.codeurjc.invoice.dtos.responses.EmployeeResponseDto;
import es.codeurjc.invoice.services.InvoiceService;
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
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Operation(summary = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all employees",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EmployeeResponseDto.class)))})})
    @GetMapping("/")
    public Collection<EmployeeResponseDto> getEmployees() {
        return this.invoiceService.findAll();
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
            return this.invoiceService.findById(Long.parseLong(employeeId));
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
        return this.invoiceService.save(employeeRequestDto);
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
        return this.invoiceService.delete(id);
    }
}
