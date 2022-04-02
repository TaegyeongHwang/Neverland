package com.faraday.neverland.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Destination {

    @Id @GeneratedValue
    @Column(name = "destination_no")
    private Long no;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private String information;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

}
