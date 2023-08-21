package com.example.projekatSvt.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserLoginDto implements Serializable {

    private String username;
    private String password;

    public UserLoginDto() {
    }
}
