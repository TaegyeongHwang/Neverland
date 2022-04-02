package com.faraday.neverland.service;

import com.faraday.neverland.domain.Account;
import com.faraday.neverland.domain.Destination;
import com.faraday.neverland.domain.Travel;
import com.faraday.neverland.domain.TravelDestination;
import com.faraday.neverland.form.TravelRegisterForm;
import com.faraday.neverland.repository.AccountRepository;
import com.faraday.neverland.repository.DestinationRepository;
import com.faraday.neverland.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TravelService {

    private final AccountRepository accountRepository;
    private final TravelRepository travelRepository;
    private final DestinationRepository destinationRepository;

    private final HttpSession session;

    /**
     * 여행코스
     */
    // 여행코스 등록하기
    @Transactional
    public Long travelRegisterProc(TravelRegisterForm form) {

        String id = (String) session.getAttribute("id");

        Account account = accountRepository.findAccount(id);
        Destination destination = destinationRepository.findDestinationOne(form.getDestinationNo());

        TravelDestination travelDestination = TravelDestination.createTravelDestination(destination);
        Travel travel = Travel.createTravel(form, account, travelDestination);

        travelRepository.persistTravel(travel);

        return travel.getNo();
    }

    // 여행코스 목록 불러오기
    public List<Travel> findTravelList() {

        return travelRepository.findTravelList();
    }

}
