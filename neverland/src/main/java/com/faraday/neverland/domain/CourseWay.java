package com.faraday.neverland.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class CourseWay {

    @Id @GeneratedValue
    @Column(name = "course_way_no")
    private Long no;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_no")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startway_no")
    private StartWay startWay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endway_no")
    private EndWay endWay;


}
