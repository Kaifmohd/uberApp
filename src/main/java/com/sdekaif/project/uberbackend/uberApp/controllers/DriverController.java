package com.sdekaif.project.uberbackend.uberApp.controllers;

import com.sdekaif.project.uberbackend.uberApp.dto.RideDto;
import com.sdekaif.project.uberbackend.uberApp.dto.RideStartDto;
import com.sdekaif.project.uberbackend.uberApp.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable("rideRequestId") Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> startRide(
            @PathVariable("rideRequestId") Long rideRequestId,
            @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(rideRequestId,rideStartDto.getOtp()));
    }
}
