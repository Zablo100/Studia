package com.example.samolot.Controllers;

import com.example.samolot.Models.KlientModel;
import com.example.samolot.Services.KlientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/klient")
public class KlientController {

    private KlientService klientService;

    @Autowired
    public KlientController(KlientService klientService) {
        this.klientService = klientService;
    }

    @GetMapping()
    List<KlientModel> getKlients(){
        return klientService.getAllKlients();
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<KlientModel> getKlient(@PathVariable Integer id){
        return ResponseEntity.of(klientService.getKlientById(id));
    }

    @GetMapping(value = "find/{name}")
    ResponseEntity<KlientModel> getKlientByName(@PathVariable String name){
        return ResponseEntity.of(klientService.getKlientByName(name));
    }
}
