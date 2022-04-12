package com.faraday.neverland.service;

import com.faraday.neverland.domain.*;
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

    private final TravelRepository travelRepository;
    private final AccountRepository accountRepository;
    private final DestinationRepository destinationRepository;

    private final HttpSession session;

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

    // 내 여행지 불러오기
    public List<Travel> myTravelPage() {

        String loginId = (String) session.getAttribute("id");

        return travelRepository.findMyTravelList(loginId);
    }

    /**
     * Admin
     */
    // 여행신청목록 불러오기
    public List<Travel> travelRegisterList() {

        return travelRepository.findTravelList();
    }

    // 방문상태 변경
    @Transactional
    public void travelStatusChangeProc(Long no, String status) {

        Travel travel = travelRepository.findTravel(no);

        if (status.equals("BLOCK")) {
            travel.setStatus(TravelStatus.BLOCK);
        } else if (status.equals("PASS")) {
            travel.setStatus(TravelStatus.PASS);
        } else {
            travel.setStatus(TravelStatus.FAIL);
        }
    }

    // API Travel 불러오기
    public Travel apiMyTravel(Long no) {

        return travelRepository.findTravel(no);
    }

}
