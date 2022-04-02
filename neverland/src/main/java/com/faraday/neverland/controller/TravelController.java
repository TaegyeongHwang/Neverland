package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Destination;
import com.faraday.neverland.domain.Travel;
import com.faraday.neverland.form.TravelRegisterForm;
import com.faraday.neverland.service.DestinationService;
import com.faraday.neverland.service.TravelService;
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
public class TravelController {

    private final TravelService travelService;
    private final DestinationService destinationService;

    /**
     * 여행코스
     */
    // 여행코스 등록 페이지
    @GetMapping("/travel/register")
    public String travelRegisterPage(Model model) {
        log.info("travelRegisterPage()");

        List<Destination> destinationList = destinationService.destinationList();

        model.addAttribute("travelRegisterForm", new TravelRegisterForm());
        model.addAttribute("destinationList", destinationList);

        return "/travel/travelRegister";
    }

    // 여행코스 등록하기
    @PostMapping("/travel/registerProc")
    public String travelRegisterProc(@Valid TravelRegisterForm form, BindingResult result, Model model) {
        log.info("travelRegisterProc()");

        if(result.hasErrors()) {
            return "/travel/travelRegister";
        }

        travelService.travelRegisterProc(form);

        return "redirect:/";
    }

    // 여행코스 목록 불러오기
    @GetMapping("/travel/list")
    public String travelListPage(Model model) {
        log.info("travelListPage()");

        List<Travel> travelList = travelService.findTravelList();

        model.addAttribute("travelList", travelList);

        return "/travel/travelList";
    }

}
