package es.codeurjc.users.controllers;

import es.codeurjc.users.dtos.requests.UserRequestDto;
import es.codeurjc.users.dtos.responses.UserResponseDto;
import es.codeurjc.users.services.UserService;
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
@RequestMapping("/api/v1/customers")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all users",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))})})
    @GetMapping("/")
    public Collection<UserResponseDto> getUsers() {
        return this.userService.findAll();
    }

    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public UserResponseDto getUser(@Parameter(description = "id of user to be searched")
                                   @PathVariable long id) {
        return this.userService.findById(id);
    }

    @Operation(summary = "Create a new user")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User to be created", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserRequestDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid user attributes supplied",
                    content = @Content)})
    @PostMapping("/")
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return this.userService.save(userRequestDto);
    }

    @Operation(summary = "Deletes user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public UserResponseDto deleteUser(@Parameter(description = "id of user to be deleted") @PathVariable long id) {
        return this.userService.delete(id);
    }

}
