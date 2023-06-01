package com.example.projekt.services;

import com.example.projekt.models.ApiModels.PrinterInvoiceRequest;
import com.example.projekt.models.Invoice;
import com.example.projekt.models.Item;
import com.example.projekt.models.Printer;
import com.example.projekt.repo.InvoiceRepo;
import com.example.projekt.repo.PrinterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrinterServiceImpl implements PrinterService{

    private final PrinterRepo repo;
    private final InvoiceRepo invoiceRepo;

    @Override
    public List<Printer> getAll() {
        return repo.findAll().stream().toList();
    }

    @Override
    public void changeStatus(int id) {
        var printer = repo.findById(id).get();
        printer.setTest(!printer.isTest());
        repo.save(printer);
    }

    @Override
    public void clearAll() {
        var printers = repo.findAll().stream().toList();
        for(var printer : printers){
            printer.setTest(false);
        }

        repo.saveAll(printers);
    }

    @Override
    public void generateInvoice(PrinterInvoiceRequest request) {
            final String seller = "Arcus S.A";
            double vat = request.getItemRequest().getPrice() * 0.23;
            double totalPrice = request.getItemRequest().getPrice() + vat;

        totalPrice = Math.round(totalPrice*100.0)/100.0;


        Invoice invoice = new Invoice();
        invoice.setName(request.getFv());
        invoice.setSeller(seller);
        invoice.setDate(LocalDate.now());
        invoice.setTotalCost(totalPrice);

        Item item = new Item();
        item.setName(request.getItemRequest().getName());
        item.setDescription(request.getItemRequest().getDescription());
        item.setQuantity(request.getItemRequest().getQuantity());
        item.setPrice(request.getItemRequest().getPrice());

        List<Item> items = new ArrayList<>();
        items.add(item);
        invoice.setItems(items);

        invoiceRepo.save(invoice);

    }


}
