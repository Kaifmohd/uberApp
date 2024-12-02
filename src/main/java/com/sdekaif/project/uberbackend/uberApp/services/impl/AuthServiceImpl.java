package com.sdekaif.project.uberbackend.uberApp.services.impl;

import com.sdekaif.project.uberbackend.uberApp.dto.DriverDto;
import com.sdekaif.project.uberbackend.uberApp.dto.SignUpDto;
import com.sdekaif.project.uberbackend.uberApp.dto.UserDto;
import com.sdekaif.project.uberbackend.uberApp.entities.Rider;
import com.sdekaif.project.uberbackend.uberApp.entities.User;
import com.sdekaif.project.uberbackend.uberApp.entities.enums.UserRole;
import com.sdekaif.project.uberbackend.uberApp.exceptions.RuntimeConflictException;
import com.sdekaif.project.uberbackend.uberApp.repositories.UserRepository;
import com.sdekaif.project.uberbackend.uberApp.services.AuthService;
import com.sdekaif.project.uberbackend.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;
    @Override
    public String login(String username, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignUpDto signUpDto) {
        userRepository.findByEmail(signUpDto.getEmail()).orElseThrow(()->
                new RuntimeConflictException("Cannot signup, User already exists with email "+ signUpDto.getEmail()));
        User mappedUser = modelMapper.map(signUpDto,User.class);
        mappedUser.setRoles(Set.of(UserRole.RIDER));
        User savedUser = userRepository.save(mappedUser);

        //Onboarding a rider(user)
        // create user related entities

        //Rider
        Rider rider = riderService.createNewRider(savedUser);

        //Wallet
        //TODO add wallet related service here

        return modelMapper.map(savedUser,UserDto.class);

    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
