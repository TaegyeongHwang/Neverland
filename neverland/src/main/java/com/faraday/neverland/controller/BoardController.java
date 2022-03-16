package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Member;
import com.faraday.neverland.form.WriteForm;
import com.faraday.neverland.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final MemberService memberService;

    @GetMapping("/board/write")
    public String writePage(Model model) {
        log.info("writePage()");

        Member member = memberService.memberInfo();

        model.addAttribute("member", member);
        model.addAttribute("writeForm", new WriteForm());

        return "board/boardWrite";
    }

}
