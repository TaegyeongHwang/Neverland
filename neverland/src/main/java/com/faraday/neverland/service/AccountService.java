package com.faraday.neverland.service;

import com.faraday.neverland.domain.Account;
import com.faraday.neverland.form.AccountJoinForm;
import com.faraday.neverland.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String accountJoinProc(AccountJoinForm form) {

        Account account = new Account();
        account.setId(form.getId());
        account.setPwd(form.getPwd());
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

    // 전체회원 불러오기
    public List<Account> accountList() {

        return accountRepository.findAccountList();
    }

    // 회원정보 불러오기
    public Account myAccountPage() {

        String loginId = (String) session.getAttribute("id");

        return accountRepository.findAccount(loginId);
    }

    // 회원정보 불러오기 22
    public Account myAccountPage(String id) {

        return accountRepository.findAccount(id);
    }

}
