package com.sdekaif.project.uberbackend.uberApp.repositories;

import com.sdekaif.project.uberbackend.uberApp.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// ST_DISTANCE(point1,point2)
// ST_DWITHIN(Point1,10000)

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query("SELECT d,ST_Distance(d.current_location,:pickupLocation) AS distance " +
            "FROM drivers AS d " +
            "where d.available=true AND ST_DWithin(d.current_location,:pickupLocation,10000) " +
            "ORDER BY d.distance " +
            "LIMIT 10"
    )
    List<Driver> findTenNearestMatchingDrivers(Point pickupLocation);
}
