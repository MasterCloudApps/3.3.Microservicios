package es.codeurjc.loyalty.controllers;

import es.codeurjc.loyalty.dtos.responses.LoyaltyResponseDto;
import es.codeurjc.loyalty.services.LoyaltyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/users")
public class LoyaltyController {

    private LoyaltyService loyaltyService;

    public LoyaltyController(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all users",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LoyaltyResponseDto.class)))})})
    @GetMapping("/")
    public Collection<LoyaltyResponseDto> getUsers() {
        return this.loyaltyService.findAll();
    }

    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoyaltyResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping("/{userId}")
    public LoyaltyResponseDto getUser(@Parameter(description = "id of user to be searched")
    @PathVariable String userId) {
            return this.loyaltyService.findById(Long.parseLong(userId));
    }
}
