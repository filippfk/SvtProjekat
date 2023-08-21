package com.example.projekatSvt.services;

import com.example.projekatSvt.dto.PasswordDto;
import com.example.projekatSvt.dto.UserDto;
import com.example.projekatSvt.entity.Roles;
import com.example.projekatSvt.entity.User;
import com.example.projekatSvt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    @Override
    public User findUserById(Long id) {
        return this.userRepository.findUserById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        Optional<User> user = userRepository.findFirstByUsername(username);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }

    @Override
    public User createUser(UserDto UserDto) {

        Optional<User> user = userRepository.findFirstByUsername(UserDto.getUsername());

        if(user.isPresent()){
            return null;
        }

        User newUser = new User();
        newUser.setUsername(UserDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(UserDto.getPassword()));
        newUser.setRole(Roles.USER);
        newUser.setEmail(UserDto.getEmail());
        newUser.setFirstName(UserDto.getFirstName());
        newUser.setLastName(UserDto.getLastName());
        newUser.setLastLogin(LocalDateTime.now());

        return userRepository.save(newUser);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<UserDto> changePassword(PasswordDto passwords) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = this.findUserByUsername(a.getName());

        if(encoder.matches(passwords.getCurrent(), user.getPassword()) && passwords.getConfirm().equals(passwords.getPassword())){
            user.setPassword(encoder.encode(passwords.getPassword()));
            this.saveUser(user);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        UserDto userDto = new UserDto(user);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @Override
    public void ChangeUserPassword(String username,String password) {
        Optional<User> user = userRepository.findFirstByUsername(username);

        User u=  user.get();
        u.setPassword(passwordEncoder.encode(password));
        userRepository.save(u);
    }

}