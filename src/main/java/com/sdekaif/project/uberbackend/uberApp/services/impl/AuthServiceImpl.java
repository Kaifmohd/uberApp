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
import com.sdekaif.project.uberbackend.uberApp.services.AuthService;
import com.sdekaif.project.uberbackend.uberApp.services.DriverService;
import com.sdekaif.project.uberbackend.uberApp.services.RiderService;
import com.sdekaif.project.uberbackend.uberApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    @Override
    public String login(String username, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignUpDto signUpDto) {
        User user = userRepository.findByEmail(signUpDto.getEmail()).orElse(null);
        if(user != null) {
            throw new RuntimeConflictException("Cannot signup, User already exists with email "+ signUpDto.getEmail());
        }
        User mappedUser = modelMapper.map(signUpDto,User.class);
        mappedUser.setRoles(Set.of(UserRole.RIDER));
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
}
