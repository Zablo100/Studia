package com.example.projekt.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Computer {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String cpu;
    private double cpuScore;
    private int cpuMaxclockSpeed;
    private int cpuCores;
    private int ram;
    private int ramSpeed;
    private String os;
    private String gpu;
    private String model;
    private Date timeOfPurchase;
    @Column(nullable = true)
    private Integer employeeId;

    @OneToMany(targetEntity = HardDrive.class, cascade = CascadeType.ALL)
    @JoinColumn(name="ch_fk", referencedColumnName = "id")
    private List<HardDrive> hardDrives;

    @OneToMany(targetEntity = Monitor.class, cascade = CascadeType.ALL)
    @JoinColumn(name="cm_fk", referencedColumnName = "id")
    private List<Monitor> monitors;
}
