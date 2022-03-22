package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Arrival;
import com.faraday.neverland.domain.Departure;
import com.faraday.neverland.domain.Member;
import com.faraday.neverland.form.RecordForm;
import com.faraday.neverland.service.ArrivalService;
import com.faraday.neverland.service.CourseService;
import com.faraday.neverland.service.DepartureService;
import com.faraday.neverland.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final MemberService memberService;
    private final CourseService courseService;
    private final DepartureService departureService;
    private final ArrivalService arrivalService;

    /**
     * 코스
     */
    // 코스 등록하기 페이지
    @GetMapping("/course/record")
    public String recordPage(Model model) {
        log.info("recordPage()");

        Member member = memberService.findMember();
        List<Departure> departureList = departureService.departureList();
        List<Arrival> arrivalList = arrivalService.arrivalList();

        model.addAttribute("member", member);
        model.addAttribute("departureList", departureList);
        model.addAttribute("arrivalList", arrivalList);

        return "course/courseRecord";
    }

    // 코스 등록하기
    @PostMapping("/course/record")
    public String recordProc(@RequestParam("memberId") String id,
                             @RequestParam("departNo") Long departNo,
                             @RequestParam("arrivalNo") Long arrivalNo) {

        courseService.courseRecord(id, departNo, arrivalNo);

        return "redirect:/";
    }


}
