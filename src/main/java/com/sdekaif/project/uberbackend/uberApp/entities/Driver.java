package com.sdekaif.project.uberbackend.uberApp.entities;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "idx_driver_vehicle_id",columnList = "vehicleId")
})
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

    private Boolean available;

    private String vehicleId;

    //Added the Point which is from jts.geom package also added the column defination which is compulsory as it will help(It says that we are dealing with earth's (long,lat))
    @Column(columnDefinition = "geometry(Point, 4326)")
    Point currentLocation;



}
