package com.example.samolot.Repository;

import com.example.samolot.Models.KlientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KlientRepository extends JpaRepository<KlientModel, Integer> {

    Optional<KlientModel> findByName(String name);
}
