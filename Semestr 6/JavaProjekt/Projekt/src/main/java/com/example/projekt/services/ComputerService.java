package com.example.projekt.services;

import com.example.projekt.models.ApiModels.ComputersResponse;
import com.example.projekt.models.Computer;

import java.util.List;

public interface ComputerService {
    public List<ComputersResponse> getAllComputers();
    public Computer getById(int id);
}
