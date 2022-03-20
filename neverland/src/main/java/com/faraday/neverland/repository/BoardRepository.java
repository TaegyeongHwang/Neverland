package com.faraday.neverland.repository;

import com.faraday.neverland.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void writeBoard(Board board) {

        em.persist(board);
    }

    public List<Board> listBoard() {

        return em.createQuery("select b from Board b", Board.class).getResultList();
    }

    public Board findConents(Long pageNo) {

        return em.find(Board.class, pageNo);
    }

}
