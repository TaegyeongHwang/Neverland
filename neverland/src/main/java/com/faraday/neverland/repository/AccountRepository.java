package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final EntityManager em;

    public Account findAccount(String id) {

        return em.find(Account.class, id);
    }

    public void persistAccount(Account account) {

        em.persist(account);
    }

}
