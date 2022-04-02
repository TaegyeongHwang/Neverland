package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Destination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DestinationRepository {

    private final EntityManager em;

    // 목적지 등록하기
    public void persistDestination(Destination destination) {

        em.persist(destination);
    }

    // 목적지 불러오기
    public List<Destination> findDestinationList() {

        return em.createQuery("select d from Destination d", Destination.class).getResultList();
    }

    // 선택한 목적지 불러오기
    public Destination findDestinationOne(Long no) {

        return em.find(Destination.class, no);
    }

}
