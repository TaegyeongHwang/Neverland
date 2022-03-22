package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Departure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class DepartureRepository {

    private final EntityManager em;

    public void persistDeparture(Departure departure) {

        em.persist(departure);
    }

}
