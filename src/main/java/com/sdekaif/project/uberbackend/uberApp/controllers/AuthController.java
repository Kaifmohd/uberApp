package com.sdekaif.project.uberbackend.uberApp.controllers;

import com.sdekaif.project.uberbackend.uberApp.dto.DriverDto;
import com.sdekaif.project.uberbackend.uberApp.dto.OnboardDriverDto;
import com.sdekaif.project.uberbackend.uberApp.dto.SignUpDto;
import com.sdekaif.project.uberbackend.uberApp.dto.UserDto;
import com.sdekaif.project.uberbackend.uberApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>(authService.signup(signUpDto), HttpStatus.CREATED);
    }

    @PostMapping("/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnboardDriverDto onboardDriverDto){
        return new ResponseEntity<>(authService.onboardNewDriver(userId,
                onboardDriverDto.getVehicleId()), HttpStatus.CREATED);
    }
}
