package com.example.projekt.controllers;

import com.example.projekt.models.ApiModels.ComputersResponse;
import com.example.projekt.models.Computer;
import com.example.projekt.models.LogSummary;
import com.example.projekt.models.PcLog;
import com.example.projekt.services.ComputerServiceImpl;
import com.example.projekt.services.PcLogServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/computer/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ComputerController {
    private final ComputerServiceImpl service;
    private final PcLogServiceImpl logService;

    @GetMapping("all")
    public ResponseEntity<List<ComputersResponse>> getAll(){
        return ResponseEntity.ok(service.getAllComputers());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Computer> getByName(@PathVariable int id){
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("get/log/{id}")
    public ResponseEntity<List<PcLog>> getPcLog(@PathVariable int id){
        return ResponseEntity.ok(logService.getByPcId(id));
    }

    @GetMapping("get/log/summary/{id}")
    public ResponseEntity<List<LogSummary>> getPcLogsummary(@PathVariable int id){
        return ResponseEntity.ok(logService.getRaportByPc(id));
    }


}
