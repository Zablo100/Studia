package com.example.projekt.repo;

import com.example.projekt.models.Computer;
import com.example.projekt.models.HardDrive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HardDriveRepo extends JpaRepository<HardDrive, Integer> {
    public List<HardDrive> findByType(String type);
}
