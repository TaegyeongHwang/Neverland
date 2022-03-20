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

    public void persistBoard(Board board) {

        em.persist(board);
    }

    public List<Board> findBoardList() {

        return em.createQuery("select b from Board b", Board.class).getResultList();
    }

    public Board findBoard(Long pageNo) {

        return em.find(Board.class, pageNo);
    }

    public void removeBoard(Board board) {

        em.remove(board);
    }

}
