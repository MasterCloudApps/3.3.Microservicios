package es.codeurjc.externalConsumer.controllers;

import es.codeurjc.externalConsumer.dtos.requests.OrderRequestDto;
import es.codeurjc.externalConsumer.dtos.responses.OrderResponseDto;
import es.codeurjc.externalConsumer.services.CommandOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/externalConsumer")
public class CommandExternalController {

    private CommandOrderService commandOrderService;

    public CommandExternalController(CommandOrderService commandOrderService) {
        this.commandOrderService = commandOrderService;
    }

    @Operation(summary = "Create a new order")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order to be created", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderRequestDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid user attributes supplied",
                    content = @Content)})
    @PostMapping("/")
    public OrderResponseDto createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        return this.commandOrderService.save(orderRequestDto);
    }

    @Operation(summary = "Deletes order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public OrderResponseDto deleteUser(@Parameter(description = "id of order to be deleted") @PathVariable long id) {
        return this.commandOrderService.delete(id);
    }

}
