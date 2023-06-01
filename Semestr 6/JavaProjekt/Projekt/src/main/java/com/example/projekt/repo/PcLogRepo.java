package com.example.projekt.repo;

import com.example.projekt.models.PcLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcLogRepo extends JpaRepository<PcLog, Integer> {
}
