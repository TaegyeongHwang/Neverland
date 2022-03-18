package com.faraday.neverland.controller;

import com.faraday.neverland.form.WriteForm;
import com.faraday.neverland.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

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
    public String writeProc(@Valid WriteForm form) {
        log.info("writeProc()");

        boardService.writeProc(form.getTitle(), form.getContents());

        return "redirect:/";
    }


}
