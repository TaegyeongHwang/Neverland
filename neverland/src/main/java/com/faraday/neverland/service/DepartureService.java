package com.faraday.neverland.service;

import com.faraday.neverland.domain.Departure;
import com.faraday.neverland.repository.DepartureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartureService {

    private final DepartureRepository departureRepository;

    /**
     * 출발장소
     */
    // 출발장소 등록
    @Transactional
    public void departRegiProc(Departure departure) {

        departureRepository.persistDeparture(departure);
    }

    // 출발장소 정보
    public List<Departure> departureList() {

        return departureRepository.findDepartureList();
    }

}
