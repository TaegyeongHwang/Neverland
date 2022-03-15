package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Member;
import com.faraday.neverland.form.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void memberJoin(Member member) {
        em.persist(member);
    }

    public Member findMember(LoginForm form) {

        return em.find(Member.class, form.getId());
    }

}
