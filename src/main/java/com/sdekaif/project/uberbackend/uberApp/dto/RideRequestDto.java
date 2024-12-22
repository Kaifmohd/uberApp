package com.sdekaif.project.uberbackend.uberApp.dto;

import com.sdekaif.project.uberbackend.uberApp.entities.enums.PaymentMethod;
import com.sdekaif.project.uberbackend.uberApp.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {

    private Long id;

    private PointDto pickupLocation;
    private PointDto dropOffLocation;
    private PaymentMethod paymentMethod;

    private LocalDateTime requestTime;

    private RiderDto rider;

    private Double fare;

    private RideRequestStatus rideRequestStatus;

}
