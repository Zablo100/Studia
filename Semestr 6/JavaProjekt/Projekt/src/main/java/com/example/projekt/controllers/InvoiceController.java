package com.example.projekt.controllers;

import com.example.projekt.models.ApiModels.InvoiceRequest;
import com.example.projekt.models.Invoice;
import com.example.projekt.services.InvoiceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceController {
    private final InvoiceServiceImpl service;

    @GetMapping("")
    public ResponseEntity<List<Invoice>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getAll(@PathVariable int id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/new")
    public ResponseEntity<HttpStatus> createNew(@RequestBody InvoiceRequest request){
        service.create(request);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
