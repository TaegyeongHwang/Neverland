package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Travel;
import com.faraday.neverland.form.DestinationRegisterForm;
import com.faraday.neverland.service.DestinationService;
import com.faraday.neverland.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final DestinationService destinationService;
    private final TravelService travelService;

    /**
     * 목적지
     */
    // 목적지 등록 페이지
    @GetMapping("/admin/destinationRegister")
    public String destinationRegisterPage(Model model) {
        log.info("destinationRegisterPage()");

        model.addAttribute("destinationRegisterForm", new DestinationRegisterForm());

        return "/admin/destinationRegister";
    }

    // 목적지 등록하기
    @PostMapping("/admin/registerProc")
    public String destinationRegisterProc(@Valid DestinationRegisterForm form, BindingResult result) {
        log.info("destinationRegisterProc");

        if(result.hasErrors()) {

            return "/admin/destinationRegister";
        }

        destinationService.destinationRegisterProc(form);

        return "redirect:/";
    }

    /**
     * 여행신청목록
     */
    // 여행신청목록 페이지
    @GetMapping("/admin/travelRegisterList")
    public String travelRegisterList(Model model) {
        log.info("travelRegisterList()");

        List<Travel> travelList = travelService.travelRegisterList();

        model.addAttribute("travelList", travelList);

        return "/admin/travelRegisterList";
    }

    // 방문상태 변경
    @PostMapping("/admin/travelStatus/{travelNo}/{travelStatus}")
    public String travelStatusChangeProc(@PathVariable("travelNo") Long travelNo, @PathVariable("travelStatus") String travelStatus) {
        log.info("travelStatusChangeProc");

        travelService.travelStatusChangeProc(travelNo, travelStatus);

        return "redirect:/admin/travelRegisterList";
    }

}
