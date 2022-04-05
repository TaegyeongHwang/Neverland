package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Travel;
import com.faraday.neverland.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final TravelService travelService;

    /**
     * Admin
     */
    // 여행신청목록 페이지
    @GetMapping("/admin/travelRegisterList")
    public String travelRegisterList(Model model) {
        log.info("travelRegisterList()");

        List<Travel> travelList = travelService.travelRegisterList();

        model.addAttribute("travelList", travelList);

        return "/admin/travelRegisterList";
    }
}
