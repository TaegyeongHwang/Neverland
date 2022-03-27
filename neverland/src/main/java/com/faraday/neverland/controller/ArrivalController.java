package com.faraday.neverland.controller;

import com.faraday.neverland.domain.Arrival;
import com.faraday.neverland.form.ArrivalForm;
import com.faraday.neverland.form.DepartureForm;
import com.faraday.neverland.service.ArrivalService;
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
public class ArrivalController {

    private final ArrivalService arrivalService;

    /**
     * 도착장소
     */
    // 출발장소 등록 페이지
    @GetMapping("/arrival/register")
    public String arrivalRegiPage(Model model) {
        log.info("arrivalRegiPage()");
        model.addAttribute("arrivalForm", new DepartureForm());

        return "arrival/arrivalRegister";
    }

    // 출발장소 등록
    @PostMapping("/arrival/register")
    public String arrivalRegiProc(@Valid ArrivalForm form, BindingResult result) {
        log.info("arrivalRegiProc()");

        if (result.hasErrors()) {

            return "arrival/arrivalRegister";
        }

        Arrival arrival = new Arrival();
        arrival.setName(form.getName());
        arrival.setInformation(form.getInformation());

        arrivalService.arrivalRegiProc(arrival);

        return "redirect:/";
    }

}
