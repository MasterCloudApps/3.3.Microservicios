package es.codeurjc.customer.controllers;

import es.codeurjc.customer.dtos.requests.CustomerRequestDto;
import es.codeurjc.customer.dtos.responses.CustomerResponseDto;
import es.codeurjc.customer.services.CustomerService;
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
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Get all customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all customer",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CustomerResponseDto.class)))})})
    @GetMapping("/")
    public Collection<CustomerResponseDto> getCustomers() {
        return this.customerService.findAll();
    }

    @Operation(summary = "Get a customer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customer",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "customer not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public CustomerResponseDto getCustomer(@Parameter(description = "id of customer to be searched")
                                   @PathVariable long id) {
        return this.customerService.findById(id);
    }

    @Operation(summary = "Create a new customer")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Customer to be created", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerRequestDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the customer",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid customer attributes supplied",
                    content = @Content)})
    @PostMapping("/")
    public CustomerResponseDto createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return this.customerService.save(customerRequestDto);
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
    @PostMapping("/{customerId}")
    public CustomerResponseDto updateCustomerStatus(@Parameter(description = "id of customer to update the status")
                                                    @PathVariable long customerId,
                                                    @Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return this.customerService.updateStatus(customerId, customerRequestDto);
    }

    @Operation(summary = "Deletes customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "customer not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public CustomerResponseDto deleteCustomer(@Parameter(description = "id of customer to be deleted") @PathVariable long id) {
        return this.customerService.delete(id);
    }

}
