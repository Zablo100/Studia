package com.example.samolot.UI;

import com.example.samolot.Models.MiejsceModel;
import com.example.samolot.Services.MiejsceService;
import com.example.samolot.Services.RezerwacjaService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Miejsca")
@Route(value = "/kupione")
public class MainUI extends VerticalLayout {

    MainUI(){
        Paragraph p = new Paragraph("Bilety kupione!");
        add(p);
    }

}
