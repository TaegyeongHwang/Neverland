package com.faraday.neverland.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter @Setter
public class WriteForm {

    @NotEmpty(message = "아이디는 필수입니다!")
    private String id;

    @NotEmpty(message = "제목은 필수입니다!")
    private String title;

    private String contents;

    private LocalDateTime writeDate;

}
