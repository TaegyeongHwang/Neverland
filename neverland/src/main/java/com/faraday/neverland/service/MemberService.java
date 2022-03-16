package com.faraday.neverland.service;

import com.faraday.neverland.domain.Member;
import com.faraday.neverland.form.LoginForm;
import com.faraday.neverland.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final HttpSession session;

    /**
     * 회원가입
     */

    public String checkId(String id) {

        String result = null;

        Member getMember = memberRepository.findMember(id);

        if(getMember == null) {
            result = "true";
        } else {
            result = "false";
        }

        return result;
    }

    @Transactional
    public void joinProc(Member member) {

        memberRepository.memberJoin(member);
    }

}
