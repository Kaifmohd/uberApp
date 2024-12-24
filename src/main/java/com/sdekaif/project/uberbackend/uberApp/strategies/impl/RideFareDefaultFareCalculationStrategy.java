package com.sdekaif.project.uberbackend.uberApp.strategies.impl;

import com.sdekaif.project.uberbackend.uberApp.entities.RideRequest;
import com.sdekaif.project.uberbackend.uberApp.services.DistanceService;
import com.sdekaif.project.uberbackend.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance  = distanceService.calculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropOffLocation());

        return distance*RIDE_FARE_MULTIPLIER;
    }
}
