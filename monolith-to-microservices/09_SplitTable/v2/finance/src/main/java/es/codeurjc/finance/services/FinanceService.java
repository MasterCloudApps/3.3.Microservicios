package es.codeurjc.finance.services;

import java.util.Collection;

import es.codeurjc.finance.dtos.requests.FinanceRequestDto;
import es.codeurjc.finance.dtos.responses.FinanceResponseDto;

public interface FinanceService {

    Collection<FinanceResponseDto> findAll();

    FinanceResponseDto save(FinanceRequestDto financeRequestDto);

    FinanceResponseDto findById(long userId);

    FinanceResponseDto updateStatus(long userId, FinanceRequestDto financeRequestDto);

    FinanceResponseDto delete(long userId);

}
