package com.sdekaif.project.uberbackend.uberApp.services;


import com.sdekaif.project.uberbackend.uberApp.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}