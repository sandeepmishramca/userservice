package com.myorg.userservice.controllers;

import com.myorg.userservice.dtos.*;
import com.myorg.userservice.models.Token;
import com.myorg.userservice.models.User;
import com.myorg.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")  // so that root api
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    public SignUpResponseDto signup(@RequestBody SignUpRequestDto requestDto) {
        User user = userService.signUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );
        SignUpResponseDto responseDto = new SignUpResponseDto();
        responseDto.setUser(user);
        return responseDto;
    }
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto) {
        Token token = userService.login(
                requestDto.getEmail(),
                requestDto.getPassword()
        );
        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setToken(token);
        return responseDto;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        userService.logOut(logoutRequestDto.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/validate")
    public UserDto validate(@RequestHeader("Authorization") String token) {
        User user = userService.validate(token);
        return UserDto.fromUser(user);
    }

}
