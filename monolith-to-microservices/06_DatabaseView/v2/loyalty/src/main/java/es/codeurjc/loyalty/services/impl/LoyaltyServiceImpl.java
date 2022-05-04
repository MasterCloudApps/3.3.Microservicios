package es.codeurjc.loyalty.services.impl;

import es.codeurjc.loyalty.dtos.requests.LoyaltyRequestDto;
import es.codeurjc.loyalty.dtos.responses.LoyaltyResponseDto;
import es.codeurjc.loyalty.exceptions.UserNotFoundException;
import es.codeurjc.loyalty.models.LoyaltyView;
import es.codeurjc.loyalty.repositories.LoyaltyRepository;
import es.codeurjc.loyalty.services.LoyaltyService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class LoyaltyServiceImpl implements LoyaltyService {

    private Mapper mapper;
    private LoyaltyRepository userRepository;

    public LoyaltyServiceImpl(Mapper mapper, LoyaltyRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public Collection<LoyaltyResponseDto> findAll() {
        return this.userRepository.findAll().stream()
                .map(user -> this.mapper.map(user, LoyaltyResponseDto.class))
                .collect(Collectors.toList());
    }

    public LoyaltyResponseDto save(LoyaltyRequestDto loyaltyRequestDto) {
        LoyaltyView user = this.mapper.map(loyaltyRequestDto, LoyaltyView.class);
        user = this.userRepository.save(user);
        return this.mapper.map(user, LoyaltyResponseDto.class);
    }

    public LoyaltyResponseDto findById(long userId) {
        LoyaltyView user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return this.mapper.map(user, LoyaltyResponseDto.class);
    }
    public LoyaltyResponseDto delete(long userId) {
        LoyaltyView user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        this.userRepository.delete(user);
        return this.mapper.map(user, LoyaltyResponseDto.class);
    }

}
