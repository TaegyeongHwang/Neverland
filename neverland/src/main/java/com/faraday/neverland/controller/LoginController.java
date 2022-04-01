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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    private final HttpSession session;

    /**
     * 로그인
     */
    // 로그인 페이지
    @GetMapping("/account/login")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());

        return "/account/accountLogin";
    }

    // 로그인
    @PostMapping("/account/loginProc")
    public String loginProc(@Valid LoginForm form, BindingResult result, RedirectAttributes rttr) {
        log.info("loginProc()");

        String view = null;

        if (result.hasErrors()) {
            return "/account/accountLogin";
        }

        view = loginService.loginProc(form, rttr);

        return view;

    }

    // 로그아웃
    @GetMapping("/account/logout")
    public String logoutProc() {
        log.info("logoutProc");

        session.invalidate();

        return "redirect:/";
    }

}
