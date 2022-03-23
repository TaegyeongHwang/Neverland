package com.faraday.neverland.service;

import com.faraday.neverland.domain.*;
import com.faraday.neverland.form.RecordForm;
import com.faraday.neverland.repository.ArrivalRepository;
import com.faraday.neverland.repository.CourseRepository;
import com.faraday.neverland.repository.DepartureRepository;
import com.faraday.neverland.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {

    private final AccountRepository accountRepository;
    private final CourseRepository courseRepository;
    private final DepartureRepository departureRepository;
    private final ArrivalRepository arrivalRepository;

    private final HttpSession session;

    /**
     * 코스
     */
    // 코스 등록하기
    @Transactional
    public void courseRecord(RecordForm form) {

        String id = (String) session.getAttribute("id");

        Account account = accountRepository.findAccount(id);
        Departure departure = departureRepository.findDeparture(form.getDepartNo());
        Arrival arrival = arrivalRepository.findArrival(form.getArrivalNo());

        CourseSchedule courseSchedule = CourseSchedule.createCourseSchedule(departure, arrival);

        Course course = Course.createCourse(account, courseSchedule, form.getTitle(), form.getContents());

        courseRepository.persistCourse(course);

    }

}
