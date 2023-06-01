package com.example.projekt.services;

import com.example.projekt.models.ApiModels.InvoiceRequest;
import com.example.projekt.models.Invoice;
import com.example.projekt.models.Item;
import com.example.projekt.repo.InvoiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService{
    private final InvoiceRepo repo;


    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoices = new java.util.ArrayList<>(repo.findAll().stream().sorted(Comparator.comparing(Invoice::getDate)).toList());
        Collections.reverse(invoices);

        return invoices;
    }

    @Override
    public Invoice getById(int id) {
        return repo.findById(id).get();
    }

    @Override
    public void create(InvoiceRequest request) {
        Invoice invoice = new Invoice();
        invoice.setName(request.getName());
        invoice.setSeller(request.getSeller());
        invoice.setDate(LocalDate.now());
        List<Item> items = new ArrayList<>();

        double totalPrice = 0;
        for(var e : request.getItems()){
            Item item = new Item();
            item.setPrice(e.getPrice());
            item.setQuantity(e.getQuantity());
            item.setDescription(e.getDescription());
            item.setName(e.getName());

            totalPrice += e.getPrice() * e.getQuantity();
            items.add(item);
        }

        double vat = totalPrice * 0.23;
        totalPrice += vat;
        totalPrice = Math.round(totalPrice*100.0)/100.0;

        invoice.setTotalCost(totalPrice);
        invoice.setItems(items);

        repo.save(invoice);

    }


}
