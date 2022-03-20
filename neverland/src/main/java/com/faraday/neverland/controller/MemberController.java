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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     */
    // 회원가입 페이지
    @GetMapping("/member/join")
    public String joinPage(Model model) {
        log.info("joinPage()");
        model.addAttribute("memberForm", new MemberForm());

        return "member/memberJoin";
    }

    // 아이디 중복확인
    @GetMapping(value = "/checkId", produces = "application/text; charset=utf-8")
    @ResponseBody
    public String checkOverlapId(String id) {
        log.info("checkOverlapId()");

        String result = memberService.checkOverlapId(id);

        return result;
    }

    // 회원가입
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
        member.setEmail(form.getEmail());

        memberService.joinProc(member);

        return "redirect:/";
    }

    /**
     * 로그인
     */
    // 로그인 페이지
    @GetMapping("/member/login")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());

        return "member/memberLogin";
    }

    // 로그인
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

    /**
     * 회원정보
     */
    // 회원정보 페이지
    @GetMapping("/member/info")
    public String infoPage(Model model) {
        log.info("infoPage()");

        Member member = memberService.infoPage();
        model.addAttribute("member", member);

        return "member/memberInfo";
    }

}
