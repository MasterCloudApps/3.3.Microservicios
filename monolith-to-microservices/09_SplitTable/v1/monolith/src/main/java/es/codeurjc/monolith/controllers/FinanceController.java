package es.codeurjc.monolith.controllers;

import es.codeurjc.monolith.dtos.requests.CustomerRequestDto;
import es.codeurjc.monolith.dtos.requests.FinanceRequestDto;
import es.codeurjc.monolith.dtos.responses.CustomerResponseDto;
import es.codeurjc.monolith.dtos.responses.FinanceResponseDto;
import es.codeurjc.monolith.services.FinanceService;
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
@RequestMapping("/api/v1/finance")
public class FinanceController {

    private FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @Operation(summary = "Get all finance info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all finance",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FinanceResponseDto.class)))})})
    @GetMapping("/")
    public Collection<FinanceResponseDto> getFinances() {
        return this.financeService.findAll();
    }

    @Operation(summary = "Get a finance by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the finance",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FinanceResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Finance not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public FinanceResponseDto getFinance(@Parameter(description = "id of finance to be searched")
                                   @PathVariable long id) {
        return this.financeService.findById(id);
    }

    @Operation(summary = "Create a new finance")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Finance to be created", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = FinanceRequestDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FinanceResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid user attributes supplied",
                    content = @Content)})
    @PostMapping("/")
    public FinanceResponseDto createFinance(@Valid @RequestBody FinanceRequestDto financeRequestDto) {
        return this.financeService.save(financeRequestDto);
    }

    @Operation(summary = "Updates customer's status")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Status to be updated", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer with updated status",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid status supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)})
    @PatchMapping("/{customerId}")
    public FinanceResponseDto updateCustomerStatus(@Parameter(description = "id of customer to update the status")
                                                    @PathVariable long customerId,
                                                    @Valid @RequestBody FinanceRequestDto financeRequestDto) {
        return this.financeService.updateStatus(customerId, financeRequestDto);
    }

    @Operation(summary = "Deletes finance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Finance deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FinanceResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Finance not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public FinanceResponseDto deleteFinance(@Parameter(description = "id of finance to be deleted") @PathVariable long id) {
        return this.financeService.delete(id);
    }

}
