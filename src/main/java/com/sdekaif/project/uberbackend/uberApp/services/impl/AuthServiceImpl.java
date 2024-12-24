package com.sdekaif.project.uberbackend.uberApp.services.impl;

import com.sdekaif.project.uberbackend.uberApp.dto.DriverDto;
import com.sdekaif.project.uberbackend.uberApp.dto.SignUpDto;
import com.sdekaif.project.uberbackend.uberApp.dto.UserDto;
import com.sdekaif.project.uberbackend.uberApp.entities.Driver;
import com.sdekaif.project.uberbackend.uberApp.entities.Rider;
import com.sdekaif.project.uberbackend.uberApp.entities.User;
import com.sdekaif.project.uberbackend.uberApp.entities.enums.UserRole;
import com.sdekaif.project.uberbackend.uberApp.exceptions.ResourceNotFoundException;
import com.sdekaif.project.uberbackend.uberApp.exceptions.RuntimeConflictException;
import com.sdekaif.project.uberbackend.uberApp.repositories.UserRepository;
import com.sdekaif.project.uberbackend.uberApp.security.JWTService;
import com.sdekaif.project.uberbackend.uberApp.services.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.sdekaif.project.uberbackend.uberApp.entities.enums.UserRole.DRIVER;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;

    @Override
    public String[] login(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);


        return new String[]{accessToken,refreshToken};
    }

    @Override
    public UserDto signup(SignUpDto signUpDto) {
        User user = userRepository.findByEmail(signUpDto.getEmail()).orElse(null);
        if(user != null) {
            throw new RuntimeConflictException("Cannot signup, User already exists with email "+ signUpDto.getEmail());
        }
        User mappedUser = modelMapper.map(signUpDto,User.class);
        mappedUser.setRoles(Set.of(UserRole.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);

        //Onboarding a rider(user)
        // create user related entities

        //Rider
        Rider rider = riderService.createNewRider(savedUser);

        //Wallet

        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser,UserDto.class);

    }

    @Override
    public DriverDto onboardNewDriver(Long userId,String vehicleId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + userId));

        if(user.getRoles().contains(DRIVER)) throw new RuntimeConflictException("User with id: "+ userId+" already a DRIVER");

        Driver createDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();

        user.getRoles().add(DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(createDriver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userService.getUserById(userId);

        return jwtService.generateAccessToken(user);
    }
}
