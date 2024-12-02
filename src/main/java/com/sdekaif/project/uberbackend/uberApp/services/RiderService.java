package com.sdekaif.project.uberbackend.uberApp.services;

import com.sdekaif.project.uberbackend.uberApp.dto.DriverDto;
import com.sdekaif.project.uberbackend.uberApp.dto.RideDto;
import com.sdekaif.project.uberbackend.uberApp.dto.RideRequestDto;
import com.sdekaif.project.uberbackend.uberApp.dto.RiderDto;
import com.sdekaif.project.uberbackend.uberApp.entities.Rider;
import com.sdekaif.project.uberbackend.uberApp.entities.User;

import java.util.List;

public interface RiderService {


    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateRider(Long rideId, Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();

    Rider createNewRider(User user);
}
