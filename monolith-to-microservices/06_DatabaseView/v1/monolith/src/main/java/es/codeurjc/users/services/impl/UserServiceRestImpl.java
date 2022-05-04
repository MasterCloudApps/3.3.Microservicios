package es.codeurjc.users.services.impl;

import es.codeurjc.users.dtos.requests.UserRequestDto;
import es.codeurjc.users.dtos.responses.UserResponseDto;
import es.codeurjc.users.exceptions.UserNotFoundException;
import es.codeurjc.users.exceptions.UserWithSameNickException;
import es.codeurjc.users.models.User;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceRestImpl {

    private Mapper mapper;
    private String usersServiceUrl;
    private RestTemplate restTemplate;


    public UserServiceRestImpl(Mapper mapper, @Value("${users.service.url}") String usersServiceUrl,
        RestTemplate restTemplate) {
        this.mapper = mapper;
        this.usersServiceUrl = usersServiceUrl + "/api/v1/users";
        this.restTemplate = restTemplate;
    }

    public Collection<UserResponseDto> findAll() {

        ResponseEntity<List<UserResponseDto>> responseEntity = restTemplate.exchange(
            usersServiceUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {
            });

        return responseEntity.getBody();
    }

    public UserResponseDto save(UserRequestDto userRequestDto) {

        ResponseEntity<List<UserResponseDto>> responseEntity = restTemplate.exchange(
            usersServiceUrl + "/" + userRequestDto.getId(),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {
            });


        if (!Objects.requireNonNull(responseEntity.getBody()).isEmpty()) {
            throw new UserWithSameNickException();
        }


        User user = this.mapper.map(userRequestDto, User.class);

        restTemplate.postForEntity(
            usersServiceUrl,
            userRequestDto,
            UserResponseDto.class);

        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto findById(long userId) {
        ResponseEntity<UserResponseDto> responseEntity = restTemplate.exchange(
            usersServiceUrl + "/" + userId,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {
            });

        return responseEntity.getBody();
    }

    public UserResponseDto findById(Integer id) {
        ResponseEntity<UserResponseDto> responseEntity = restTemplate.exchange(
            usersServiceUrl + "/" + id,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {
            });

        return responseEntity.getBody();
    }

    public UserResponseDto delete(long id) {
        UserResponseDto user = this.findById(id);

        if (user == null) {
            throw new UserNotFoundException();
        }

        ResponseEntity<UserResponseDto> responseEntity = restTemplate.exchange(
            usersServiceUrl + "/" + id,
            HttpMethod.DELETE,
            null,
            new ParameterizedTypeReference<>() {
            });

        return this.mapper.map(user, UserResponseDto.class);
    }

}
