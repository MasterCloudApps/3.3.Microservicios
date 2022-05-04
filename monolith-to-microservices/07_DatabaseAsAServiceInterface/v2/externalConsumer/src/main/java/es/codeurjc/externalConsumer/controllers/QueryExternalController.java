package es.codeurjc.externalConsumer.controllers;

import es.codeurjc.externalConsumer.dtos.requests.OrderRequestDto;
import es.codeurjc.externalConsumer.dtos.responses.OrderResponseDto;
import es.codeurjc.externalConsumer.services.QueryOrderService;
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
@RequestMapping("/api/v1/externalConsumer")
public class QueryExternalController {

    private QueryOrderService queryOrderService;

    public QueryExternalController(QueryOrderService queryOrderService) {
        this.queryOrderService = queryOrderService;
    }

    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all orders",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class)))})})
    @GetMapping("/")
    public Collection<OrderResponseDto> getUsers() {
        return this.queryOrderService.findAll();
    }

    @Operation(summary = "Get a order by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)})
    @GetMapping("/{userId}")
    public OrderResponseDto getUser(@Parameter(description = "id of order to be searched")
    @PathVariable String userId) {
            return this.queryOrderService.findById(Long.parseLong(userId));
    }

}
