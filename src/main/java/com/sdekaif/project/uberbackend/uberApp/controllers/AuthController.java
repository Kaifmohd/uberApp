package com.sdekaif.project.uberbackend.uberApp.controllers;

import com.sdekaif.project.uberbackend.uberApp.dto.SignUpDto;
import com.sdekaif.project.uberbackend.uberApp.dto.UserDto;
import com.sdekaif.project.uberbackend.uberApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    UserDto signUp(@RequestBody SignUpDto signUpDto){
        return authService.signup(signUpDto);
    }
}
