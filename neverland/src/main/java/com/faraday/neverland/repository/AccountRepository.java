package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final EntityManager em;

    // 전체회원 불러오기
    public List<Account> findAccountList() {

        return em.createQuery("select a from Account a", Account.class).getResultList();
    }

    // 회원정보 조회
    public Account findAccount(String id) {

        return em.find(Account.class, id);
    }

    // 회원가입
    public void persistAccount(Account account) {

        em.persist(account);
    }

}
