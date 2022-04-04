package com.faraday.neverland.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Travel {

    @Id @GeneratedValue
    @Column(name = "travel_no")
    private Long no;

    @Enumerated(EnumType.STRING)
    private TravelStatus status;

    private LocalDateTime issueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<TravelDestination> travelDestinations = new ArrayList<>();

    // 연관관계 메소드
    public void setAccount(Account account) {
        this.account = account;
        account.getTravel().add(this);
    }

    public void addTravelDestination(TravelDestination travelDestination) {
        travelDestinations.add(travelDestination);
        travelDestination.setTravel(this);
    }

    public static Travel createTravel(Account account, TravelDestination travelDestination) {
        Travel travel = new Travel();
        travel.setAccount(account);
        travel.addTravelDestination(travelDestination);
        travel.setStatus(TravelStatus.BLOCK);
        travel.setIssueDate(LocalDateTime.now());

        return travel;
    }

}
