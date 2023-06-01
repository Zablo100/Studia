package com.example.projekt.repo;

import com.example.projekt.models.Printer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrinterRepo extends JpaRepository<Printer,Integer> {
}
