package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Travel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class TravelRepository {

    private final EntityManager em;

    // 여행지 신청
    public void persistTravel(Travel travel) {

        em.persist(travel);
    }
}
