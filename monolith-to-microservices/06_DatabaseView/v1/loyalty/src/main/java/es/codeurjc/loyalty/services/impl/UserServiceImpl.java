package es.codeurjc.loyalty.services.impl;

import es.codeurjc.loyalty.dtos.requests.UserRequestDto;
import es.codeurjc.loyalty.dtos.responses.UserResponseDto;
import es.codeurjc.loyalty.exceptions.UserNotFoundException;
import es.codeurjc.loyalty.models.User;
import es.codeurjc.loyalty.repositories.UserRepository;
import es.codeurjc.loyalty.services.UserService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Mapper mapper;
    private UserRepository userRepository;

    public UserServiceImpl(Mapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public Collection<UserResponseDto> findAll() {
        return this.userRepository.findAll().stream()
                .map(user -> this.mapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = this.mapper.map(userRequestDto, User.class);
        user = this.userRepository.save(user);
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto findById(long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return this.mapper.map(user, UserResponseDto.class);
    }
    public UserResponseDto delete(long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        this.userRepository.delete(user);
        return this.mapper.map(user, UserResponseDto.class);
    }

}
