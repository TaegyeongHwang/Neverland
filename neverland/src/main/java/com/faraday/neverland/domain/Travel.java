package com.faraday.neverland.domain;

import com.faraday.neverland.form.TravelRegisterForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Travel {

    @Id @GeneratedValue
    @Column(name = "travel_no")
    private Long no;

    @Column(length = 100, nullable = false)
    private String title;

    private String contents;

    @Enumerated(EnumType.STRING)
    private TravelStatus status;

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

    // 생성 메소드
    public static Travel createTravel(TravelRegisterForm form, Account account, TravelDestination travelDestination) {
        Travel travel = new Travel();
        travel.setTitle(form.getTitle());
        travel.setContents(form.getContents());
        travel.setStatus(TravelStatus.BLOCK);
        travel.setAccount(account);
        travel.addTravelDestination(travelDestination);

        return travel;
    }
}
