package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Departure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartureRepository {

    private final EntityManager em;

    public void persistDeparture(Departure departure) {

        em.persist(departure);
    }

    public List<Departure> findDepartureList() {

        return em.createQuery("select d from Departure d", Departure.class).getResultList();
    }

    public Departure findDeparture(Long no) {

        return em.find(Departure.class, no);
    }

}
