package com.faraday.neverland.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class WriteForm {

    @NotEmpty(message = "제목은 필수입니다!")
    private String title;

    @NotEmpty(message = "내용은 필수입니다!")
    private String contents;

}
