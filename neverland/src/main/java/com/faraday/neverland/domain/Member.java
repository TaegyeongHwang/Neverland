package com.faraday.neverland.domain;

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

    @OneToMany(mappedBy = "member")
    private List<Board> board = new ArrayList<>();
}