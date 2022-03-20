package com.faraday.neverland.service;

import com.faraday.neverland.domain.Board;
import com.faraday.neverland.domain.Member;
import com.faraday.neverland.repository.BoardRepository;
import com.faraday.neverland.repository.MemberRepository;
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

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    private final HttpSession session;

    /**
     * 게시판
     */
    // 글쓰기
    @Transactional
    public Long writeProc(String title, String contents) {

        String id = (String)session.getAttribute("id");

        Member member = memberRepository.findMember(id);

        Board board = new Board();
        board.setMember(member);
        board.setTitle(title);
        board.setContents(contents);
        board.setWriteDate(LocalDateTime.now());

        boardRepository.writeBoard(board);

        return board.getNo();
    }

    public List<Board> boardList() {

        return boardRepository.listBoard();
    }

    public Board contentsList(Long pageNo) {

        return boardRepository.findConents(pageNo);
    }

    @Transactional
    public void updateWrite(Long no, String title, String contents) {

        Board board = boardRepository.findConents(no);
        board.setTitle(title);
        board.setContents(contents);
    }

}
