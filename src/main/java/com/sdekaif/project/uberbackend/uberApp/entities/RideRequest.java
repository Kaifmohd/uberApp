package com.sdekaif.project.uberbackend.uberApp.entities;

import com.sdekaif.project.uberbackend.uberApp.entities.enums.PaymentMethod;
import com.sdekaif.project.uberbackend.uberApp.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        indexes = {
                @Index(name = "idx_ride_request_rider",columnList = "rider_id")
        }
)
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime requestTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus;

    private Double fare;

}
