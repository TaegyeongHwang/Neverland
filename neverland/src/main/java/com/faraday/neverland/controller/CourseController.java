package com.faraday.neverland.controller;

import com.faraday.neverland.domain.*;
import com.faraday.neverland.form.RegisterForm;
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
    @GetMapping("/course/register")
    public String registerPage(Model model) {
        log.info("registerPage()");

        Account account = accountService.findAccount();
        List<Departure> departureList = departureService.departureList();
        List<Arrival> arrivalList = arrivalService.arrivalList();

        model.addAttribute("registerForm", new RegisterForm());
        model.addAttribute("account", account);
        model.addAttribute("departureList", departureList);
        model.addAttribute("arrivalList", arrivalList);

        return "course/courseRegister";
    }

    // 코스 등록하기
    @PostMapping("/course/register")
    public String registerProc(@Valid RegisterForm form, BindingResult result) {
        log.info("registerProc()");

        if (result.hasErrors()) {

            return "course/courseRegister";
        }

        courseService.courseRegister(form);

        return "redirect:/";
    }

    // 코스 목록보기
    @GetMapping("/course/registerList")
    public String registerList(Model model) {
        log.info("registerList()");

        List<Course> registerList = courseService.registerList();
        model.addAttribute("registerList", registerList);

        return "course/courseList";
    }

}
