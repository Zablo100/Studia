package com.example.projekt.controllers;

import com.example.projekt.models.Employee;
import com.example.projekt.services.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
       private final EmployeeServiceImpl service;

    @GetMapping("all")
    public ResponseEntity<List<Employee>> getAll(){
           return ResponseEntity.ok(service.getAll());
       }

    @GetMapping("get/{id}")
    public ResponseEntity<Employee> getById(@PathVariable int id){
        return ResponseEntity.ok(service.getById(id));
    }


}
