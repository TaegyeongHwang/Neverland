package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Board;
import com.faraday.neverland.form.BoardWriteForm;
import com.faraday.neverland.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시판
     */
    // 게시판 페이지
    @GetMapping("/board/list")
    public String boardListPage(Model model) {
        log.info("boardListPage()");

        List<Board> boardList = boardService.boardList();
        model.addAttribute("boardList", boardList);

        return "/board/boardList";
    }

    /**
     * 글쓰기
     */
    // 글쓰기 페이지
    @GetMapping("/board/write")
    public String boardWritePage(Model model) {
        log.info("boardWritePage()");

        model.addAttribute("boardWriteForm", new BoardWriteForm());

        return "/board/boardWrite";
    }

    // 글쓰기
    @PostMapping("/board/writeProc")
    public String boardWriteProc(@Valid BoardWriteForm form, BindingResult result, Model model) {
        log.info("boardWriteProc()");

        if (result.hasErrors()) {

            return "/board/boardWrite";
        }

        boardService.boardWriteProc(form.getTitle(), form.getContents());

        return "redirect:/";
    }

    /**
     * 상세내용
     */
    // 상세내용 페이기
    @GetMapping("/board/contents")
    public String boardContentsPage(Long no, Model model) {
        log.info("boardContentsPage()" + no);

        Board board = boardService.boardContentsPage(no);
        model.addAttribute("board", board);

        return "/board/boardContents";
    }

    // 수정하기 페이지
    @GetMapping("/board/update")
    public String boardUpdatePage(Long no, Model model) {
        log.info("boardUpdatePage()" + no);

        Board boardUpdate = boardService.boardUpdatePage(no);
        model.addAttribute("boardUpdate", boardUpdate);

        return "/board/boardUpdate";
    }

    // 수정하기
    @PostMapping("/board/update")
    public String boardUpdateProc(Long no, BoardWriteForm form, Model model) {
        log.info("boardUpdateProc()" + no);

        boardService.boardUpdateProc(no, form.getTitle(), form.getContents());

        return "redirect:/";
    }

    // 삭제하기
    @GetMapping("/board/delete")
    public String boardDeleteProc(Long no, Model model) {
        log.info("boardDeleteProc()" + no);

        boardService.boardDeleteProc(no);

        return "redirect:/";
    }

}
