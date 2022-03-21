package com.faraday.neverland.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @Column(name = "member_id", length = 100)
    private String id;

    @Column(length = 100, nullable = false)
    private String pw;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 250, nullable = false)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Board> board = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Course> course = new ArrayList<>();
}
