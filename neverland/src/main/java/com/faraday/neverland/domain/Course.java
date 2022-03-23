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
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseSchedule> courseSchedules = new ArrayList<>();

    public void setAccount(Account account) {
        this.account = account;
        account.getCourse().add(this);
    }

    public void addCourseSchedule(CourseSchedule courseSchedule) {
        courseSchedules.add(courseSchedule);
        courseSchedule.setCourse(this);
    }

    public static Course createCourse(Account account, CourseSchedule courseSchedule, String title, String contents) {

        Course course = new Course();
        course.setAccount(account);
        course.addCourseSchedule(courseSchedule);
        course.setTitle(account.getId() + "의 " + title);
        course.setContents(account.getId() + "의 " + contents);

        return course;
    }
}
