package com.example.projekatSvt.controller;

import com.example.projekatSvt.dto.PassDto;
import com.example.projekatSvt.dto.UserDto;
import com.example.projekatSvt.dto.UserLoginDto;
import com.example.projekatSvt.entity.User;
import com.example.projekatSvt.security.TokenUtils;
import com.example.projekatSvt.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;

    /* Ili preporucen nacin: Constructor Dependency Injection
    @Autowired
    public UserController(UserServiceImpl userService, AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService, TokenUtils tokenUtils){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenUtils = tokenUtils;
    }
    */
    @PostMapping("/register")
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto newUser){

        User createdUser = userService.createUser(newUser);

        if(createdUser == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto userDto = new UserDto(createdUser);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    public ResponseEntity<UserTokenState> createAuthenticationToken(
//            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
//
//        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
//        // AuthenticationException
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//
//        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
//        // kontekst
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Kreiraj token za tog korisnika
//        UserDetails user = (UserDetails) authentication.getPrincipal();
//        String jwt = tokenUtils.generateToken(user);
//        int expiresIn = tokenUtils.getExpiredIn();
//
//        // Vrati token kao odgovor na uspesnu autentifikaciju
//        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto userDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> loadAll() {
        return this.userService.findAll();
    }

    @GetMapping("/whoami")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public User user(Principal user) {
        return this.userService.findUserByUsername(user.getName());
    }

//    @PostMapping("/changePassword")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<UserDTO> changePassword (@RequestBody @Validated PasswordDTO passwords) {
//        return userService.changePassword(passwords);
//    }

    @PostMapping("/changePassword")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public HttpStatus user(Principal user,@RequestBody  String dto ) throws JsonProcessingException {
        User found = this.userService.findUserByUsername(user.getName());
        ObjectMapper mapper = new ObjectMapper();
        PassDto passValue = mapper.readValue(dto, PassDto.class);

        if(passwordEncoder.matches(passValue.getOldPassword1(),found.getPassword()))
        {


            this.userService.ChangeUserPassword(user.getName(),passValue.getNewPassword());
            return HttpStatus.ACCEPTED;
        }
        else return HttpStatus.NOT_ACCEPTABLE;

    }
}
