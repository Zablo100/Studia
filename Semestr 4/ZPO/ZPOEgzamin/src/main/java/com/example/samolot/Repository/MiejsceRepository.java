package com.example.samolot.Repository;

import com.example.samolot.Models.MiejsceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiejsceRepository extends JpaRepository<MiejsceModel, Integer> {
}
