package com.example.projekt.repo;

import com.example.projekt.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
