package com.faraday.neverland.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class CourseSchedule {

    @Id @GeneratedValue
    @Column(name = "course_schedule_no")
    private Long no;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_no")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_no")
    private Departure departure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_no")
    private Arrival arrival;


}
