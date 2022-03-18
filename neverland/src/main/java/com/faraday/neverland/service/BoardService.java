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

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    private final HttpSession session;

    /**
     * 글쓰기
     */
    // 글쓰기
    @Transactional
    public Long writeProc(String title, String contents) {

        Member member = (Member) session.getAttribute("member");

        Board board = new Board();
        board.setMember(member);
        board.setTitle(title);
        board.setContents(contents);
        board.setWriteDate(LocalDateTime.now());

        boardRepository.writeBoard(board);

        return board.getNo();
    }

}
