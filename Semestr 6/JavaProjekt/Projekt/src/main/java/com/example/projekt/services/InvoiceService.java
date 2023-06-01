package com.example.projekt.services;

import com.example.projekt.models.ApiModels.InvoiceRequest;
import com.example.projekt.models.Invoice;

import java.util.List;

public interface InvoiceService {
    public List<Invoice> getAll();
    public Invoice getById(int id);
    public void create(InvoiceRequest request);
}
