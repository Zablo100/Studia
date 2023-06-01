package com.example.projekt.models.ApiModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {
    private String name;
    private String description;
    private int quantity;
    private double price;
}
