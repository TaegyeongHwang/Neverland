package com.faraday.neverland.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class RegisterForm {

    @NotEmpty(message = "코스이름은 필수입니다!")
    private String title;

    @NotEmpty(message = "코스이름은 필수입니다!")
    private String contents;

    private Long departNo;

    private Long arrivalNo;
}
