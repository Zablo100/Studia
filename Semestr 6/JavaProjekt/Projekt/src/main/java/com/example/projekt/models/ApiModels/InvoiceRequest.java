package com.example.projekt.models.ApiModels;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InvoiceRequest {
    private String name;
    private String seller;
    private List<ItemRequest> items;
}
