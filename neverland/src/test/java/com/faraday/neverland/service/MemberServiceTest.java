package com.faraday.neverland.service;

import com.faraday.neverland.domain.Member;
import com.faraday.neverland.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member= new Member();
        member.setId("TestId");
        member.setPw("1234");
        member.setName("TestName");

        // when
        String saveId = memberService.joinProc(member);

        // then
        assertThat(member).isEqualTo(memberRepository.findMember(saveId));
    }

}