package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Arrival;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArrivalRepository {

    private final EntityManager em;

    public void persistArrival(Arrival arrival) {

        em.persist(arrival);
    }

    public List<Arrival> findArrivalList() {

        return em.createQuery("select a from Arrival a", Arrival.class).getResultList();
    }

    public Arrival findArrival(Long no) {

        return em.find(Arrival.class, no);
    }

}
