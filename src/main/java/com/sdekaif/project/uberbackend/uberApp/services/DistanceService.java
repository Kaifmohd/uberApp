package com.sdekaif.project.uberbackend.uberApp.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {

    double calculateDistance(Point src, Point dest);
    //we will choose OSRM or GoogleMaps depending on how its working.
}
