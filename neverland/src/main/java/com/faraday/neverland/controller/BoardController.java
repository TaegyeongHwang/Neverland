package com.faraday.neverland.controller;

import com.faraday.neverland.form.WriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    /**
     * 게시판
     */
    // 글쓰기 페이지
    @GetMapping("/board/write")
    public String writePage(Model model) {
        model.addAttribute("writeForm", new WriteForm());

        return "board/boardWrite";
    }


}
