package com.sdekaif.project.uberbackend.uberApp.strategies;

import com.sdekaif.project.uberbackend.uberApp.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.sdekaif.project.uberbackend.uberApp.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.sdekaif.project.uberbackend.uberApp.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.sdekaif.project.uberbackend.uberApp.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
    private final RideFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
        if(riderRating >= 4.8) {
            return highestRatedDriverStrategy;
        }else{
            return nearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        // Peak hour surging  6pm to 9pm
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();
        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime){
            return surgePricingFareCalculationStrategy;
        }else{
            return defaultFareCalculationStrategy;
        }
    }

}
