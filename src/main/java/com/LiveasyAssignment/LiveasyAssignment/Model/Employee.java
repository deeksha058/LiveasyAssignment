package com.LiveasyAssignment.LiveasyAssignment.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EmployeeId")
    private UUID Id;

    @Column(name = "EmployeeName" ,nullable = false)
    private String EmployeeName;
    @Column(name = "PhoneNumber" ,nullable = false)
    private String PhoneNumber ;
    @Column(name = "Email" , nullable = false)
    private String Email;
    @Column(name = "ReportsTo")
    private UUID ReportsTo;
    @Column(name = "ProfileImage", nullable = false)
    private String ProfileImage;

}
