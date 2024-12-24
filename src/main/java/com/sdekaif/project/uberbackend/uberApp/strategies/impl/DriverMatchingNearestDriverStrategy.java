package com.sdekaif.project.uberbackend.uberApp.strategies.impl;

import com.sdekaif.project.uberbackend.uberApp.entities.Driver;
import com.sdekaif.project.uberbackend.uberApp.entities.RideRequest;
import com.sdekaif.project.uberbackend.uberApp.repositories.DriverRepository;
import com.sdekaif.project.uberbackend.uberApp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearestMatchingDrivers(rideRequest.getPickupLocation());
    }
}
