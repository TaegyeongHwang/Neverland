package com.faraday.neverland.api;

import com.faraday.neverland.domain.Travel;
import com.faraday.neverland.domain.TravelStatus;
import com.faraday.neverland.service.TravelService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TravelApiController {
    private final TravelService travelService;

    @GetMapping("/api/travel/status")
    public APITravel apiMyTravel(@RequestBody @Valid UpdateTravelStatus request) {
        travelService.travelStatusChangeProc(request.getTravelNo(), request.getStatus());
        Travel travel = travelService.apiMyTravel(request.getTravelNo());

        return new APITravel(travel.getStatus());
    }

    @Data
    static class UpdateTravelStatus {
        private Long travelNo;
        private String status;
    }

    @Data
    @AllArgsConstructor
    static class APITravel {
        private TravelStatus status;
    }
}
