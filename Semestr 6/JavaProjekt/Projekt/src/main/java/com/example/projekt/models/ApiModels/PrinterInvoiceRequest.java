package com.example.projekt.models.ApiModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrinterInvoiceRequest {
    private String fv;
    private ItemRequest itemRequest;
}
