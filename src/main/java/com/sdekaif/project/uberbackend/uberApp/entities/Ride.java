package com.sdekaif.project.uberbackend.uberApp.entities;

import com.sdekaif.project.uberbackend.uberApp.entities.enums.PaymentMethod;
import com.sdekaif.project.uberbackend.uberApp.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ride {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(columnDefinition = "geometry(Point, 4326)")
        private Point pickupLocation;

        @Column(columnDefinition = "geometry(Point, 4326)")
        private Point dropOffLocation;

        @CreationTimestamp
        private LocalDateTime createdTime;

        @ManyToOne(fetch = FetchType.LAZY)
        private Rider rider;

        @ManyToOne(fetch = FetchType.LAZY)
        private Driver driver;

        @Enumerated(EnumType.STRING)
        private PaymentMethod paymentMethod;

        @Enumerated(EnumType.STRING)
        private RideStatus rideStatus;

        private Double fare;
        private LocalDateTime startedAt;
        private LocalDateTime endedAt;
        private String otp;
}
