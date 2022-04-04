package com.faraday.neverland.service;

import com.faraday.neverland.domain.Account;
import com.faraday.neverland.domain.Destination;
import com.faraday.neverland.domain.Travel;
import com.faraday.neverland.domain.TravelDestination;
import com.faraday.neverland.repository.AccountRepository;
import com.faraday.neverland.repository.DestinationRepository;
import com.faraday.neverland.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;
    private final AccountRepository accountRepository;
    private final DestinationRepository destinationRepository;

    /**
     * 여행지
     */
    // 여행지 신청
    @Transactional
    public Long travelRegisterProc(String loginId, Long destinationNo) {

        Account account = accountRepository.findAccount(loginId);
        Destination destination = destinationRepository.findDestinationContents(destinationNo);

        TravelDestination travelDestination = TravelDestination.createTravelDestination(destination);
        Travel travel = Travel.createTravel(account, travelDestination);

        travelRepository.persistTravel(travel);

        return travel.getNo();
    }

}
