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

    // 여행지 신청
    public void persistTravel(Travel travel) {

        em.persist(travel);
    }

    // 여행지 불러오기
    public Travel findTravel(Long no) {

        return em.find(Travel.class, no);
    }

    // 내 여행지 불러오기
    public List<Travel> findMyTravelList(String loginId) {

        return em.createQuery("select t from Travel t where t.account.id = :loginId", Travel.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }

    // 여행신청목록 불러오기
    public List<Travel> findTravelList() {

        return em.createQuery("select t from Travel t", Travel.class).getResultList();
    }
}
