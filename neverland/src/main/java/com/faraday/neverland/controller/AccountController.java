package com.faraday.neverland.controller;

import com.faraday.neverland.form.AccountForm;
import com.faraday.neverland.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    /**
     * 회원가입
     */
    // 회원가입 페이지
    @GetMapping("/account/join")
    public String joinPage(Model model) {
        log.info("joinPage()");
        model.addAttribute("accountForm", new AccountForm());

        return "/account/accountJoin";
    }

    // 아이디 중복확인
    @GetMapping(value = "/checkId", produces = "application/text; charset=utf-8")
    @ResponseBody
    public String checkOverlapId(String id) {
        log.info("checkOverlapId()");

        String result = accountService.checkOverlapId(id);

        return result;
    }

    // 회원가입
    @PostMapping("/account/join")
    public String joinProc(@Valid AccountForm form, BindingResult result) {
        log.info("joinProc()");

        if (result.hasErrors()) {

            return "/account/accountJoin";
        }

        accountService.joinProc(form);

        return "/home";
    }

}
