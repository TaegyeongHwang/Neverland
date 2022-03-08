package com.faraday.neverland.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_no")
    private Long no;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 100, nullable = false)
    private String title;

    private String contents;

    private LocalDateTime writeDate;
}
