package com.faraday.neverland.controller;

import com.faraday.neverland.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TravelController {

    private final TravelService travelService;

    /**
     * 여행지
     */
    // 여행지 신청
    @PostMapping("/travel/register/{loginId}/{destinationNo}")
    public String travelRegisterProc(@PathVariable("loginId") String loginId, @PathVariable("destinationNo") Long destinationNo) {
        log.info("travelRegisterProc" + destinationNo);

        travelService.travelRegisterProc(loginId, destinationNo);

        return "redirect:/";
    }

}
