package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Departure;
import com.faraday.neverland.form.DepartureForm;
import com.faraday.neverland.service.DepartureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DepartureController {

    private final DepartureService departureService;

    /**
     * 출발장소
     */
    // 출발장소 등록 페이지
    @GetMapping("/departure/register")
    public String departRegiPage(Model model) {
        log.info("departRegiPage()");
        model.addAttribute("departureForm", new DepartureForm());

        return "departure/departRegister";
    }

    // 출발장소 등록
    @PostMapping("/departure/register")
    public String departRegiProc(@Valid DepartureForm form, BindingResult result) {
        log.info("departRegiProc()");

        if (result.hasErrors()) {

            return "departure/departRegister";
        }

        Departure departure = new Departure();
        departure.setName(form.getName());
        departure.setInformation(form.getInformation());

        departureService.departRegiProc(departure);

        return "redirect:/";
    }
}
