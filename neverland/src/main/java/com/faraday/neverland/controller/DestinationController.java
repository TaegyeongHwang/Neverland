package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Destination;
import com.faraday.neverland.service.DestinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DestinationController {

    private final DestinationService destinationService;

    /**
     * 목적지
     */
    // 목적지 목록 가져오기
    @GetMapping("/destination/list")
    public String destinationListPage(Model model) {
        log.info("destinationListPage()");

        List<Destination> destinationList = destinationService.destinationList();
        model.addAttribute("destinationList", destinationList);

        return "/destination/destinationList";
    }

    // 목적지 상세페이지
    @GetMapping("/destination/contents/{no}")
    public String destinationContentsPage(@PathVariable("no") Long no, Model model) {
        log.info("destinationContentsPage()" + no);

        Destination destination = destinationService.destinationContents(no);
        model.addAttribute("destination", destination);

        return "/destination/destinationContents";
    }

}
