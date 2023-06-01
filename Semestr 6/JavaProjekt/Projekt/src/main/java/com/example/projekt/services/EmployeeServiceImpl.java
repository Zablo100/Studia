package com.example.projekt.services;

import com.example.projekt.models.Employee;
import com.example.projekt.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepo employeeRepo;

    @Override
    public Employee getById(int id) {
        return employeeRepo.findById(id).get();
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepo.findAll().stream().toList();
    }
}
