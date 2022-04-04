package com.faraday.neverland.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class TravelDestination {

    @Id @GeneratedValue
    @Column(name = "trave_destination_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_no")
    private Travel travel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_no")
    private Destination destination;

    // 생성 메소드
    public static TravelDestination createTravelDestination(Destination destination) {
        TravelDestination travelDestination = new TravelDestination();
        travelDestination.setDestination(destination);

        return travelDestination;
    }

}
