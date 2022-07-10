package com.example.samolot.Repository;

import com.example.samolot.Models.RezerwacjaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezerwacjaRepository extends JpaRepository<RezerwacjaModel, Integer> {
}
