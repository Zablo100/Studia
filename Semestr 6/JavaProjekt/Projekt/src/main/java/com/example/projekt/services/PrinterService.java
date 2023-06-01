package com.example.projekt.services;


import com.example.projekt.models.ApiModels.PrinterInvoiceRequest;
import com.example.projekt.models.Printer;

import java.util.List;

public interface PrinterService {
    public List<Printer> getAll();

    public void changeStatus(int id);
    public void clearAll();
    public void generateInvoice(PrinterInvoiceRequest request);
}
