package com.example.projekt.controllers;

import com.example.projekt.models.ApiModels.PrinterInvoiceRequest;
import com.example.projekt.models.Printer;
import com.example.projekt.services.PrinterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/printer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PrinterController {

    private final PrinterServiceImpl service;

    @GetMapping("/all")
    public ResponseEntity<List<Printer>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PatchMapping("/changeStatus/{id}")
    public ResponseEntity<HttpStatus> changeStatus(@PathVariable int id){
        service.changeStatus(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/cleare")
    public ResponseEntity<HttpStatus> clearAll(){
        service.clearAll();

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/invoice")
    public ResponseEntity<HttpStatus> createInvoiceForPrinter(@RequestBody PrinterInvoiceRequest request){
        service.generateInvoice(request);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
