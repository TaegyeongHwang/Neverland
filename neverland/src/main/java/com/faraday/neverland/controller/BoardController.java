package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Board;
import com.faraday.neverland.form.WriteForm;
import com.faraday.neverland.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @GetMapping("/board")
    public String boardPage(Model model) {
        log.info("boardPage()");

        List<Board> boardList = boardService.boardList();
        model.addAttribute("boardList", boardList);

        return "board/boardList";
    }

    /**
     * 글쓰기
     */
    // 글쓰기 페이지
    @GetMapping("/board/write")
    public String writePage(Model model) {
        log.info("writePage()");

        model.addAttribute("writeForm", new WriteForm());

        return "board/boardWrite";
    }

    // 글쓰기
    @PostMapping("/board/write")
    public String writeProc(@Valid WriteForm form, BindingResult result, Model model) {
        log.info("writeProc()");

        if (result.hasErrors()) {

            return "board/boardWrite";
        }

        boardService.writeProc(form.getTitle(), form.getContents());

        return "redirect:/";
    }

    /**
     * 상세내용
     */
    // 상세내용 페이기
    @GetMapping("/board/contents")
    public String contentsPage(Long no, Model model) {
        log.info("contentsPage()" + no);

        Board board = boardService.contentsPage(no);
        model.addAttribute("board", board);

        return "/board/boardContents";
    }

    // 수정하기 페이지
    @GetMapping("/board/update")
    public String updatePage(Long no, Model model) {
        log.info("updatePage()" + no);

        Board boardUpdate = boardService.updatePage(no);
        model.addAttribute("boardUpdate", boardUpdate);

        return "/board/boardUpdate";
    }

    // 수정하기
    @PostMapping("/board/update")
    public String updateWrite(Long no, @ModelAttribute("update") WriteForm form, Model model) {
        log.info("updateWrite" + no);

        boardService.updateWrite(no, form.getTitle(), form.getContents());

        return "redirect:/";
    }

    // 삭제하기
    @GetMapping("/board/delete")
    public String deleteProc(Long no, Model model) {
        log.info("deleteProc()" + no);

        boardService.deleteProc(no);

        return "redirect:/";
    }

}
