package com.sdekaif.project.uberbackend.uberApp.repositories;

import com.sdekaif.project.uberbackend.uberApp.entities.Payment;
import com.sdekaif.project.uberbackend.uberApp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByRide(Ride ride);
}
