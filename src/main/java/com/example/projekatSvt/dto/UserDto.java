package com.example.projekatSvt.dto;

import com.example.projekatSvt.entity.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {

    private Long id;
    @NotEmpty
    @Pattern(regexp = "[A-Za-z0-9_]{3,21}$")
    private String username;
    @NotEmpty
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String email;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;


    public UserDto(User createdUser) {
        this.id = createdUser.getId();
        this.username = createdUser.getUsername();
        this.password = createdUser.getPassword();
        this.email = createdUser.getEmail();
        this.firstName = createdUser.getFirstName();
        this.lastName = createdUser.getLastName();
    }

    public UserDto() {
    }
}