package es.codeurjc.loyalty.services;

import java.util.Collection;

import es.codeurjc.loyalty.dtos.requests.UserRequestDto;
import es.codeurjc.loyalty.dtos.responses.UserResponseDto;

public interface UserService {

    Collection<UserResponseDto> findAll();

    UserResponseDto save(UserRequestDto userRequestDto);

    UserResponseDto findById(long userId);

    UserResponseDto delete(long userId);

}
