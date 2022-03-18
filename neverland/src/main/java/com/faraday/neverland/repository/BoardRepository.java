package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void writeBoard(Board board) {

        em.persist(board);
    }

}
