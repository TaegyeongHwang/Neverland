package com.faraday.neverland.service;

import com.faraday.neverland.domain.Destination;
import com.faraday.neverland.form.DestinationRegisterForm;
import com.faraday.neverland.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;

    /**
     * 목적지
     */
    // 목적지 등록하기
    @Transactional
    public Long destinationRegisterProc(DestinationRegisterForm form) {

        Destination destination = new Destination();
        destination.setName(form.getName());
        destination.setInformation(form.getInformation());
        destination.setLatitude(form.getLatitude());
        destination.setLongitude(form.getLongitude());

        destinationRepository.persistDestination(destination);

        return destination.getNo();
    }

    // 목적지 불러오기
    public List<Destination> destinationList() {

        return destinationRepository.findDestinationList();
    }

}
