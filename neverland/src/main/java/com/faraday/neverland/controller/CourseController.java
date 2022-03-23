package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Arrival;
import com.faraday.neverland.domain.Departure;
import com.faraday.neverland.domain.Account;
import com.faraday.neverland.form.RecordForm;
import com.faraday.neverland.service.ArrivalService;
import com.faraday.neverland.service.CourseService;
import com.faraday.neverland.service.DepartureService;
import com.faraday.neverland.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final AccountService accountService;
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

        Account account = accountService.findAccount();
        List<Departure> departureList = departureService.departureList();
        List<Arrival> arrivalList = arrivalService.arrivalList();

        model.addAttribute("recordForm", new RecordForm());
        model.addAttribute("account", account);
        model.addAttribute("departureList", departureList);
        model.addAttribute("arrivalList", arrivalList);

        return "course/courseRecord";
    }

    // 코스 등록하기
    @PostMapping("/course/record")
    public String recordProc(@Valid RecordForm form, BindingResult result) {
        log.info("recordProc()");

        if (result.hasErrors()) {

            return "course/courseRecord";
        }

        courseService.courseRecord(form);

        return "redirect:/";
    }

}
