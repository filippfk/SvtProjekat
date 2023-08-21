package com.example.projekatSvt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PassDto {
    private String oldPassword1;
    private String oldPassword2;
    private String newPassword;

}