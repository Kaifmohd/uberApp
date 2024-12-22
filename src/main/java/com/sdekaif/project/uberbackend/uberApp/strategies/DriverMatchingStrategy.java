package com.sdekaif.project.uberbackend.uberApp.strategies;

import com.sdekaif.project.uberbackend.uberApp.entities.Driver;
import com.sdekaif.project.uberbackend.uberApp.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
