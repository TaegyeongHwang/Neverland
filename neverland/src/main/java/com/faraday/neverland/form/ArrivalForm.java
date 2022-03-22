package com.faraday.neverland.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class ArrivalForm {

    @NotEmpty(message = "도착위치 이름은 필수입니다!")
    private String name;

    private double location;

}
