package com.example.samolot.Controllers;

import com.example.samolot.Models.RezerwacjaModel;
import com.example.samolot.Services.RezerwacjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RezerwacjaController {

    private RezerwacjaService rezerwacjaService;

    @Autowired
    public RezerwacjaController(RezerwacjaService rezerwacjaService) {
        this.rezerwacjaService = rezerwacjaService;
    }

    @GetMapping("/rezerwacje")
    public List<RezerwacjaModel> getAllReservations(){
        return rezerwacjaService.getAllReservations();
    }

    @GetMapping("/rezerwacja/{id}")
    public ResponseEntity<RezerwacjaModel> getRezerwacja(@PathVariable Integer id){
        return ResponseEntity.of(rezerwacjaService.getRezerwacja(id));
    }

    @PostMapping(path = "/rezerwacja/{name}&{id}")
    ResponseEntity<Void> createRezerwacja(@PathVariable String name, @PathVariable Integer id){
        List<Integer> lista = new ArrayList<>();
        lista.add(id);
        rezerwacjaService.addRezerwacja(name,lista);


        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/rezerwacja/{id}")
    public ResponseEntity<Void> deleteProjekt(@PathVariable Integer id) {
        return rezerwacjaService.getRezerwacja(id).map(p -> {
            rezerwacjaService.deletRezerwacja(id);
            return new ResponseEntity<Void>(HttpStatus.OK); // 200
        }).orElseGet(() -> ResponseEntity.notFound().build()); // 404 - Not found
    }

}
