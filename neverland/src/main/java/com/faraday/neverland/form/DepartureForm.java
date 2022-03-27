package com.faraday.neverland.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class DepartureForm {

    @NotEmpty(message = "출발장소 이름은 필수입니다!")
    private String name;

    private String information;

}
