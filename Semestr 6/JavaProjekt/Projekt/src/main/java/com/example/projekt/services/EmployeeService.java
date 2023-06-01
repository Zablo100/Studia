package com.example.projekt.services;

import com.example.projekt.models.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee getById(int id);
    public List<Employee> getAll();

}
