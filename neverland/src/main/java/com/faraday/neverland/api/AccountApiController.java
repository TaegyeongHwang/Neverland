package com.faraday.neverland.api;

import com.faraday.neverland.domain.Account;
import com.faraday.neverland.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountService accountService;

    @GetMapping("/api/account/{id}")
    public APIAccount apiMyAccount(@PathVariable("id") String id) {

        Account account = accountService.myAccountPage(id);

        AccountDto accountDto = new AccountDto(account.getId(), account.getName(), account.getEmail());

        return new APIAccount(accountDto);
    }

    @Data
    @AllArgsConstructor
    static class APIAccount {
        private AccountDto accountDto;
    }

    @Data
    @AllArgsConstructor
    static class AccountDto {
        private String id;
        private String name;
        private String email;

    }
}
