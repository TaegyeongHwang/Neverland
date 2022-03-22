package com.faraday.neverland.controller;

import com.faraday.neverland.form.LoginForm;
import com.faraday.neverland.service.LoginService;
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
public class LoginController {

    private final LoginService loginService;

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

        view = loginService.loginProc(form, rttr);

        return view;

    }

}
