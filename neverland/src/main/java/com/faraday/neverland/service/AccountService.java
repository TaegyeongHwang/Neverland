package com.faraday.neverland.service;

import com.faraday.neverland.domain.Account;
import com.faraday.neverland.form.AccountForm;
import com.faraday.neverland.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final HttpSession session;

    /**
     * 회원가입
     */
    // 아이디 중복확인
    public String checkOverlapId(String id) {

        String result = null;

        Account account = accountRepository.findAccount(id);

        if(account == null) {
            result = "true";
        } else {
            result = "false";
        }

        return result;
    }

    // 회원가입
    @Transactional
    public String joinProc(AccountForm form) {

        Account account = new Account();
        account.setId(form.getId());
        account.setPw(form.getPw());
        account.setName(form.getName());

        if(form.getEmail().equals("admin")) {
            account.setLevel(1);
            account.setEmail("admin account");
        }
        else {
            account.setEmail(form.getEmail());
        }

        accountRepository.persistAccount(account);

        return account.getId();
    }

    /**
     * 회원정보
     */
    // 로그인 정보
    public Account findAccount() {

        String id = (String) session.getAttribute("id");

        return accountRepository.findAccount(id);
    }
    // 회원정보 페이지
    public Account infoPage() {

        String id = (String) session.getAttribute("id");

        Account account = accountRepository.findAccount(id);

        return account;
    }


}
