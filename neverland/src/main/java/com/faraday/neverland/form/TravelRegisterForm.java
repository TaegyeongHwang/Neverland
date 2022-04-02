package com.faraday.neverland.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class TravelRegisterForm {

    @NotEmpty(message = "제목은 필수입니다!")
    private String title;

    private String contents;

    private Long destinationNo;

}
