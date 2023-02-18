package com.LiveasyAssignment.LiveasyAssignment.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "loads")
public class Loads {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LoadId")
    private int id;
    @Column(name = "LoadingPoint" ,nullable = false)
    private String loadingPoint;
    @Column(name = "UnloadingPoint" ,nullable = false)
    private String unloadingPoint;
    @Column(name = "ProductType" , nullable = false)
    private String productType;
    @Column(name = "TruckType", nullable = false)
    private String truckType;
    @Column(name = "NoOdTruck", nullable = false)
    private String noOfTrucks;
    @Column(name = "Weight", nullable = false)
    private String weight;
    @Column(name = "Comment")
    private String comment;
    @Column(name = "ShipperId" , nullable = false)
    private UUID shipperId;
    @Column(name = "Date" ,nullable = false)
    @JsonFormat(pattern="dd-MM-yyyy" )
    private LocalDate Date;

}
