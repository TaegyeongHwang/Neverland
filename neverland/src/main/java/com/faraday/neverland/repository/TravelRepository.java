package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Travel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TravelRepository {

    private final EntityManager em;

    public void persistTravel(Travel travel) {

        em.persist(travel);
    }

    public List<Travel> findTravelList() {

        return em.createQuery("select t from Travel t", Travel.class).getResultList();
    }
}
