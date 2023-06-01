package com.example.projekt.repo;

import com.example.projekt.models.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepo extends JpaRepository<Computer,Integer> {

}
