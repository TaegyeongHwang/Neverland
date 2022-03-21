package com.faraday.neverland.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class StartWay {

    @Id
    @GeneratedValue
    @Column(name = "startway_no")
    private Long no;

    @Column(length = 100, nullable = false)
    private String startName;

    @Column(length = 255, nullable = false)
    private double startLocation;

}
