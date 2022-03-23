package com.faraday.neverland.service;

import com.faraday.neverland.domain.Board;
import com.faraday.neverland.domain.Account;
import com.faraday.neverland.repository.BoardRepository;
import com.faraday.neverland.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final AccountRepository accountRepository;
    private final BoardRepository boardRepository;

    private final HttpSession session;

    /**
     * 게시판
     */
    // 게시판 페이지
    public List<Board> boardList() {

        return boardRepository.findBoardList();
    }

    /**
     * 글쓰기
     */
    // 글쓰기
    @Transactional
    public Long writeProc(String title, String contents) {

        String id = (String) session.getAttribute("id");

        Account account = accountRepository.findAccount(id);

        Board board = new Board();
        board.setMember(account);
        board.setTitle(title);
        board.setContents(contents);
        board.setWriteDate(LocalDateTime.now());

        boardRepository.persistBoard(board);

        return board.getNo();
    }

    /**
     * 상세내용
     */
    // 상세내용 페이지
    public Board contentsPage(Long pageNo) {

        return boardRepository.findBoard(pageNo);
    }

    // 수정하기 페이지
    public Board updatePage(Long pageNo) {

        return boardRepository.findBoard(pageNo);
    }

    // 수정하기
    @Transactional
    public void updateWrite(Long no, String title, String contents) {

        Board board = boardRepository.findBoard(no);
        board.setTitle(title);
        board.setContents(contents);
        board.setWriteDate(LocalDateTime.now());
    }

    // 삭제하기
    @Transactional
    public void deleteProc(Long no) {

        Board board = boardRepository.findBoard(no);

        boardRepository.removeBoard(board);
    }

}
