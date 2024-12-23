package com.sdekaif.project.uberbackend.uberApp.services;

import com.sdekaif.project.uberbackend.uberApp.dto.DriverDto;
import com.sdekaif.project.uberbackend.uberApp.dto.RiderDto;
import com.sdekaif.project.uberbackend.uberApp.entities.Driver;
import com.sdekaif.project.uberbackend.uberApp.entities.Ride;
import com.sdekaif.project.uberbackend.uberApp.entities.Rider;

public interface RatingService {

    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);

    void createNewRating(Ride ride);

}
