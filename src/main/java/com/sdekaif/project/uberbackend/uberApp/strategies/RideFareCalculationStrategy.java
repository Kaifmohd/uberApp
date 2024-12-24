package com.sdekaif.project.uberbackend.uberApp.strategies;

import com.sdekaif.project.uberbackend.uberApp.entities.RideRequest;

public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER = 10;

    double calculateFare(RideRequest rideRequest);
}
