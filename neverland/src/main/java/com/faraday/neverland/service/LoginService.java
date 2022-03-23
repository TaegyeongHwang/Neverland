package com.faraday.neverland.service;

import com.faraday.neverland.domain.Account;
import com.faraday.neverland.form.LoginForm;
import com.faraday.neverland.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final AccountRepository accountRepository;

    private final HttpSession session;

    /**
     * 로그인
     */
    // 로그인
    public String loginProc(LoginForm form, RedirectAttributes rttr) {
        String view = null;
        String alert = null;

        Account account = accountRepository.findAccount(form.getId());

        if (account != null) {
            if (account.getPw().equals(form.getPw())) {
                session.setAttribute("account", account);
                session.setAttribute("id", account.getId());
                view = "redirect:/";
            }
            else {
                view = "redirect:/account/login";
                alert ="비밀번호가 틀렸습니다!";
            }
        } else {
            view = "redirect:/account/login";
            alert = "아이디가 없습니다!";
        }

        rttr.addFlashAttribute("alert", alert);

        return view;
    }

}
