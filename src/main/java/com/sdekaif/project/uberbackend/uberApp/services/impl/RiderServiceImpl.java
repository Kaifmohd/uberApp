package com.sdekaif.project.uberbackend.uberApp.services.impl;

import com.sdekaif.project.uberbackend.uberApp.dto.DriverDto;
import com.sdekaif.project.uberbackend.uberApp.dto.RideDto;
import com.sdekaif.project.uberbackend.uberApp.dto.RideRequestDto;
import com.sdekaif.project.uberbackend.uberApp.dto.RiderDto;
import com.sdekaif.project.uberbackend.uberApp.entities.*;
import com.sdekaif.project.uberbackend.uberApp.entities.enums.RideRequestStatus;
import com.sdekaif.project.uberbackend.uberApp.entities.enums.RideStatus;
import com.sdekaif.project.uberbackend.uberApp.exceptions.ResourceNotFoundException;
import com.sdekaif.project.uberbackend.uberApp.repositories.RideRequestRepository;
import com.sdekaif.project.uberbackend.uberApp.repositories.RiderRepository;
import com.sdekaif.project.uberbackend.uberApp.services.DriverService;
import com.sdekaif.project.uberbackend.uberApp.services.RideService;
import com.sdekaif.project.uberbackend.uberApp.services.RiderService;
import com.sdekaif.project.uberbackend.uberApp.strategies.RideStrategyManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();

        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);

        //TODO : Send Notification to all the drivers about this ride request

        return modelMapper.map(savedRideRequest,RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);

        if(!rider.equals(ride.getRider())) {
            throw new RuntimeException("Rider is does not own this ride with id: "+ rideId);
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled, invalid status "+ ride.getRideStatus());
        }
        Ride savedRide = rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(),true);
        return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public DriverDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider,RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider= getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider,pageRequest).map(ride -> modelMapper.map(ride, RideDto.class));
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        //TODO : implement Spring Security

        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider not found" + 1));
    }
}
