package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Account;
import com.faraday.neverland.domain.Travel;
import com.faraday.neverland.form.AccountJoinForm;
import com.faraday.neverland.service.AccountService;
import com.faraday.neverland.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;
    private final TravelService travelService;

    /**
     * 회원가입
     */
    // 회원가입 페이지
    @GetMapping("/account/join")
    public String accountJoinPage(Model model) {
        log.info("accountJoinPage()");
        model.addAttribute("accountJoinForm", new AccountJoinForm());

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
    @PostMapping("/account/joinProc")
    public String accountJoinProc(@Valid AccountJoinForm form, BindingResult result) {
        log.info("accountJoinProc()");

        if (result.hasErrors()) {

            return "/account/accountJoin";
        }

        accountService.accountJoinProc(form);

        return "redirect:/";
    }

    /**
     * 회원정보
     */
    // 회원정보 불러오기
    @GetMapping("/account/myAccount")
    public String myAccountPage(Model model) {
        log.info("myAccountPage()");

        Account account = accountService.myAccountPage();
        model.addAttribute("account", account);

        return "/account/accountMyAccount";
    }

    // 내 여행지 불러오기
    @GetMapping("/account/myTravel")
    public String myTravelPage(Model model) {
        log.info("myTravelPage()");

        List<Travel> travelList = travelService.myTravelPage();
        model.addAttribute("travelList",travelList);

        return "/account/accountMyTravel";
    }

}
