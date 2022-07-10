package com.example.samolot.Controllers;

import com.example.samolot.Models.MiejsceModel;
import com.example.samolot.Services.MiejsceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MiejsceController {

    private MiejsceService miejsceService;

    @Autowired
    public MiejsceController(MiejsceService miejsceService) {
        this.miejsceService = miejsceService;
    }

    @GetMapping(value = "/miejsca")
    List<MiejsceModel> getMiejsca(){
        return miejsceService.getMiejsca();
    }

    @GetMapping("miejsce/{id}")
    ResponseEntity<MiejsceModel> getMiejsce(@PathVariable Integer id){
        return ResponseEntity.of(miejsceService.getMiejsce(id));
    }




}
