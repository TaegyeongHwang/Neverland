package com.faraday.neverland.service;

import com.faraday.neverland.domain.Arrival;
import com.faraday.neverland.repository.ArrivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArrivalService {

    private final ArrivalRepository arrivalRepository;

    /**
     * 도착장소
     */
    // 도착장소 등록
    @Transactional
    public void arrivalRegiProc(Arrival arrival) {

        arrivalRepository.persistArrival(arrival);
    }

    // 도착장소 정보
    public List<Arrival> arrivalList() {

        return arrivalRepository.findArrivalList();
    }

}
