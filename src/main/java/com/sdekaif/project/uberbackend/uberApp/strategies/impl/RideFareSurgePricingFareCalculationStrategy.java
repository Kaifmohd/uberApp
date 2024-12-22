package com.sdekaif.project.uberbackend.uberApp.strategies.impl;

import com.sdekaif.project.uberbackend.uberApp.entities.RideRequest;
import com.sdekaif.project.uberbackend.uberApp.services.DistanceService;
import com.sdekaif.project.uberbackend.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    private final DistanceService distanceService;
    private static final double SURGE_FACTOR = 2;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance  = distanceService.calculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropOffLocation());

        return distance*RIDE_FARE_MULTIPLIER*2;
    }
}
