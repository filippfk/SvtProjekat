package com.example.projekatSvt.services;

import com.example.projekatSvt.dto.PasswordDto;
import com.example.projekatSvt.dto.UserDto;
import com.example.projekatSvt.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    User findUserById(Long id);
    User findUserByUsername(String username);
    User createUser(UserDto UserDto);
    List<User> findAll();
    void saveUser(User user);

    ResponseEntity<UserDto> changePassword(PasswordDto PasswordDto);

    void ChangeUserPassword(String username, String password);
}
