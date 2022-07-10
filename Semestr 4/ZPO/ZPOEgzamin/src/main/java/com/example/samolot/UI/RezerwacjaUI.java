package com.example.samolot.UI;

import com.example.samolot.Models.KlientModel;
import com.example.samolot.Models.MiejsceModel;
import com.example.samolot.Services.KlientService;
import com.example.samolot.Services.MiejsceService;
import com.example.samolot.Services.RezerwacjaService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@PageTitle("Rezerwacja miejsca")
@Route("")
public class RezerwacjaUI extends HorizontalLayout {
    VerticalLayout siedzenieLewa = new VerticalLayout();
    VerticalLayout siedzeniaPrawa = new VerticalLayout();
    VerticalLayout sidePanel = new VerticalLayout();
    private Integer id = 1;
    private List<Integer> wybranemiejsca = new ArrayList<>();
    private List<Integer> zajeteMiejsca = new ArrayList<>();
    private List<Integer> proponowneMiejsca = new ArrayList<>();
    private List<Button> przyciskiLista = new ArrayList<>();
    //private Integer ileMiejsca = 0;

    private MiejsceService miejsceService;
    private RezerwacjaService rezerwacjaService;
    private KlientService klientService;

    List<Integer> rzadLewa = new ArrayList<Integer>();


    public RezerwacjaUI(MiejsceService miejsceService, RezerwacjaService rezerwacjaService, KlientService klientService){
        this.miejsceService = miejsceService;
        this.rezerwacjaService = rezerwacjaService;
        this.klientService = klientService;
        setLewa();
        setSidePanel();
        siedzenieLewa.setWidth("20%");
        siedzeniaPrawa.setWidth("20%");
        sidePanel.setWidth("50%");
        add(siedzenieLewa, siedzeniaPrawa, sidePanel);
        setSizeFull();
        setStatusmiejsca();

    }

    private void setSidePanel() {
        EmailField email = new EmailField("Email");
        NumberField ile = new NumberField("Wpisz ile miejsca ma zaproponować algorytm");
        ile.setHasControls(true);
        ile.setMax(5.0);
        ile.setMin(2.0);
        ile.setValue(2.0);

        Button pokaz = new Button("Zaproponuj miejsca");
        pokaz.addClickListener(e -> setProponowane(ile.getValue()));
        Button swoje = new Button("Zakup wybrane miejsca");
        swoje.addClickListener(e -> zarezerwujWybrane(email.getValue()));
        Button algorytm = new Button("Zakup proponowane miejsca");
        algorytm.addClickListener(e -> zarezerwujAlgorytm(email.getValue()));

        sidePanel.add(ile, email, pokaz, swoje, algorytm);
    }

    private void setLewa() {
        rzadLewa.add(1);
        rzadLewa.add(2);
        rzadLewa.add(3);
        rzadLewa.add(4);
        rzadLewa.forEach(rzad -> siedzenieLewa.add(setRzadLewa()));
        siedzenieLewa.setJustifyContentMode(JustifyContentMode.CENTER);
        rzadLewa.forEach(rzad -> siedzeniaPrawa.add(setRzadPrawa()));
        siedzeniaPrawa.setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private HorizontalLayout setRzadLewa() {
        Button siedzenie1 = new Button("1");
        Button siedzenie2 = new Button("2");
        Button siedzenie3 = new Button("3");

        siedzenie1.setId(id.toString());
        siedzenie1.addClickListener(e -> klik(e.getSource()));
        siedzenie1.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        przyciskiLista.add(siedzenie1);
        id++;
        siedzenie2.setId(id.toString());
        siedzenie2.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        siedzenie2.addClickListener(e -> klik(e.getSource()));
        przyciskiLista.add(siedzenie2);
        id++;
        siedzenie3.setId(id.toString());
        siedzenie3.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        siedzenie3.addClickListener(e -> klik(e.getSource()));
        przyciskiLista.add(siedzenie3);
        id++;
        HorizontalLayout x = new HorizontalLayout(siedzenie1, siedzenie2, siedzenie3);
        return x;
    }

    private HorizontalLayout setRzadPrawa() {
        Button siedzenie1 = new Button("4");
        Button siedzenie2 = new Button("5");
        Button siedzenie3 = new Button("6");

        siedzenie1.setId(id.toString());
        przyciskiLista.add(siedzenie1);
        siedzenie1.addClickListener(e -> klik(e.getSource()));
        siedzenie1.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        id++;
        siedzenie2.setId(id.toString());
        przyciskiLista.add(siedzenie2);
        siedzenie2.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        siedzenie2.addClickListener(e -> klik(e.getSource()));
        id++;
        siedzenie3.setId(id.toString());
        przyciskiLista.add(siedzenie3);
        siedzenie3.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS); //ButtonVariant.MATERIAL_OUTLINED
        siedzenie3.addClickListener(e -> klik(e.getSource()));
        id++;
        HorizontalLayout x = new HorizontalLayout(siedzenie1, siedzenie2, siedzenie3);
        return x;
    }


    private void klik(Button button) {
        Integer przyciskId = Integer.parseInt(Objects.requireNonNull(button.getId().orElse(null)));
        if (!zajeteMiejsca.contains(przyciskId)) {
        if (wybranemiejsca.contains(przyciskId )) { //Odklikaj
            wybranemiejsca.remove(przyciskId);
            button.removeThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        } else { //klikaj
            if (wybranemiejsca.size() != 3) {
                wybranemiejsca.add(przyciskId);
                button.removeThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
                button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
            }
        }
    }
    }

    private void setStatusmiejsca(){
        //Id - 1 bo lista od 0
        List<MiejsceModel> zajete = miejsceService.getMiejsca().stream().filter(MiejsceModel::isZarezerwowane).toList();

        zajete.forEach(e -> zajeteMiejsca.add(e.getId()));

        zajeteMiejsca.forEach(e -> {
            przyciskiLista.get(e-1).removeThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
            przyciskiLista.get(e-1).addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        });
    }

    private void setProponowane( Double x){
            List<MiejsceModel> proponowane = miejsceService.getObokSiebie(x);
            proponowane.forEach(e -> proponowneMiejsca.add(e.getId()));

            proponowneMiejsca.forEach(e -> {
                przyciskiLista.get(e - 1).removeThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
                przyciskiLista.get(e - 1).addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_PRIMARY);
            });
    }

    private void zarezerwujWybrane(String name){
        KlientModel klient = klientService.getKlientByName(name).orElse(new KlientModel("Brak"));
        if (Objects.equals(name, klient.getName())){
            Notification.show("Posiadasz już rezerwajce!");
        }else{
            rezerwacjaService.addRezerwacja(name, wybranemiejsca);
        }
    }

    private void zarezerwujAlgorytm(String name){
        KlientModel klient = klientService.getKlientByName(name).orElse(new KlientModel("Brak"));
        if (Objects.equals(name, klient.getName())){
            Notification.show("Posiadasz już rezerwajce!");
        }else{
            rezerwacjaService.addRezerwacja(name, proponowneMiejsca);
        }
    }
}
