package com.faraday.neverland.service;

import com.faraday.neverland.domain.Member;
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
    // 아이디 중복확인
    public String checkOverlapId(String id) {

        String result = null;

        Member getMember = memberRepository.findMember(id);

        if(getMember == null) {
            result = "true";
        } else {
            result = "false";
        }

        return result;
    }

    // 회원가입
    @Transactional
    public String joinProc(Member member) {

        memberRepository.persistMember(member);

        return member.getId();
    }

    /**
     * 회원정보
     */
    // 회원정보 페이지
    public Member infoPage() {

        String id = (String) session.getAttribute("id");

        Member member = memberRepository.findMember(id);

        return member;
    }

}
