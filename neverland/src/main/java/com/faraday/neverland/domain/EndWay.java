package com.faraday.neverland.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class EndWay {

    @Id
    @GeneratedValue
    @Column(name = "endway_no")
    private Long no;

    @Column(length = 100, nullable = false)
    private String endName;

    @Column(length = 255, nullable = false)
    private double endLocation;

}
