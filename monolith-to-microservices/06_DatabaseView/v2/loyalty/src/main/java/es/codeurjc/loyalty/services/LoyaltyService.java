package es.codeurjc.loyalty.services;

import java.util.Collection;

import es.codeurjc.loyalty.dtos.requests.LoyaltyRequestDto;
import es.codeurjc.loyalty.dtos.responses.LoyaltyResponseDto;

public interface LoyaltyService {

    Collection<LoyaltyResponseDto> findAll();

    LoyaltyResponseDto save(LoyaltyRequestDto loyaltyRequestDto);

    LoyaltyResponseDto findById(long userId);

    LoyaltyResponseDto delete(long userId);

}
