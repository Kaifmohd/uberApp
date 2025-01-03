package com.sdekaif.project.uberbackend.uberApp.services;

import com.sdekaif.project.uberbackend.uberApp.dto.DriverDto;
import com.sdekaif.project.uberbackend.uberApp.dto.SignUpDto;
import com.sdekaif.project.uberbackend.uberApp.dto.UserDto;

public interface AuthService {

    String[] login(String email, String password);


    UserDto signup(SignUpDto signUpDto);

    DriverDto onboardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}
