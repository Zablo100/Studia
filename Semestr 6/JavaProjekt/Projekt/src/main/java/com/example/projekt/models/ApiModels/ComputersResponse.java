package com.example.projekt.models.ApiModels;

import com.example.projekt.models.Computer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComputersResponse {
    private int id;
    private String name;
    private String employeeFullName;
    private String cpu;
    private double cpuScore;
    private int ram;
    private String gpu;
    private String os;
    private String hardDriveType;
    private String workType;

    public ComputersResponse(Computer computer, String employeeFullNameame){
        id = computer.getId();
        name = computer.getName();
        this.employeeFullName = employeeFullNameame;
        cpu = computer.getCpu();
        cpuScore = computer.getCpuScore();
        ram = computer.getRam();
        gpu = computer.getGpu();
        os = computer.getOs();
        hardDriveType = computer.getHardDrives().get(0).getType();
        workType = "Biuro"; // TODO: Do zmiany
    }
}
