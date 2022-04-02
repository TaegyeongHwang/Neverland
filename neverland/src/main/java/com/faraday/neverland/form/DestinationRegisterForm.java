package com.faraday.neverland.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class DestinationRegisterForm {

    @NotEmpty(message = "이름은 필수입니다!")
    private String name;

    @NotEmpty(message = "정보는 필수입니다!")
    private String information;

    private double latitude;

    private double longitude;
}
