package com.sdekaif.project.uberbackend.uberApp.strategies;

import com.sdekaif.project.uberbackend.uberApp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {

    double calculateFare(RideRequestDto rideRequestDto);
}
