package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Arrival;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ArrivalRepository {

    private final EntityManager em;

    public void persistArrival(Arrival arrival) {

        em.persist(arrival);
    }

}
