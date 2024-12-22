package com.sdekaif.project.uberbackend.uberApp.services.impl;

import com.sdekaif.project.uberbackend.uberApp.entities.RideRequest;
import com.sdekaif.project.uberbackend.uberApp.exceptions.ResourceNotFoundException;
import com.sdekaif.project.uberbackend.uberApp.repositories.RideRequestRepository;
import com.sdekaif.project.uberbackend.uberApp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId).orElseThrow(()-> new ResourceNotFoundException("Ride Request Not Found with id: "+rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        RideRequest toSave = rideRequestRepository.findById(rideRequest.getId()).orElseThrow(()-> new ResourceNotFoundException("Ride Request Not Found with id: "+rideRequest.getId()));

        rideRequestRepository.save(toSave);
    }
}
