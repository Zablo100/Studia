package com.example.projekt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Factory {
    @Id
    @GeneratedValue
    private int id;

    private String City;
    private String Address;
    private String PostalCode;

}
