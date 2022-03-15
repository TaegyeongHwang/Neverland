package com.faraday.neverland.service;

import com.faraday.neverland.domain.Member;
import com.faraday.neverland.form.LoginForm;
import com.faraday.neverland.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Transactional
    public void joinProc(Member member) {

        memberRepository.memberJoin(member);
    }

    public String loginProc(LoginForm form, RedirectAttributes rttr) {
        String view = null;
        String alert = null;

        Member member = memberRepository.findMember(form);

        if (member != null) {
            if (member.getPw().equals(form.getPw())) {
                session.setAttribute("member", member);
                view = "redirect:/";
            }
            else {
                view = "redirect:/member/login";
                alert ="비밀번호가 틀렸습니다!";
            }
        } else {
            view = "redirect:/member/login";
            alert = "아이디가 없습니다!";
        }

        rttr.addFlashAttribute("alert", alert);

        return view;
    }
}
