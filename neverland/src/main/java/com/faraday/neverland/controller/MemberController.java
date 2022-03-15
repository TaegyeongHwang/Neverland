package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Member;
import com.faraday.neverland.form.LoginForm;
import com.faraday.neverland.form.MemberForm;
import com.faraday.neverland.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/join")
    public String joinPage(Model model) {
        log.info("joinPage()");
        model.addAttribute("memberForm", new MemberForm());

        return "member/memberJoin";
    }

    @PostMapping("/member/join")
    public String joinProc(@Valid MemberForm form, BindingResult result) {
        log.info("joinProc()");

        if (result.hasErrors()) {
            return "member/memberJoin";
        }

        Member member = new Member();
        member.setId(form.getId());
        member.setPw(form.getPw());
        member.setName(form.getName());

        memberService.joinProc(member);

        return "redirect:/";
    }

    @GetMapping("/member/login")
    public String loginPage(Model model) {
        log.info("loginPage()");
        model.addAttribute("loginForm", new LoginForm());

        return "member/memberLogin";
    }

    @PostMapping("/member/login")
    public String loginProc(@Valid LoginForm form, BindingResult result, RedirectAttributes rttr) {
        log.info("loginProc()");

        String view = null;

        if (result.hasErrors()) {
            return "member/memberLogin";
        }

        view = memberService.loginProc(form, rttr);

        return view;
    }
}
