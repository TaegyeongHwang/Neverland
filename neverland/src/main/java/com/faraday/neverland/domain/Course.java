package com.faraday.neverland.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Course {

    @Id @GeneratedValue
    @Column(name = "course_no")
    private Long no;

    @Column(length = 100, nullable = false)
    private String title;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseSchedule> courseSchedules = new ArrayList<>();
}
