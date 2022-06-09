package com.project.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.project.model.Projekt;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @FXML
    private ChoiceBox<Integer> cbPageSizes;
    @FXML
    private TableView<Projekt> tblProjekt;
    @FXML
    private TableColumn<Projekt, Integer> colId;
    @FXML
    private TableColumn<Projekt, String> colNazwa;
    @FXML
    private TableColumn<Projekt, String> colOpis;
    @FXML
    private TableColumn<Projekt, LocalDateTime> colDataCzasUtworzenia;
    @FXML
    private TableColumn<Projekt, LocalDate> colDataOddania;
    @FXML
    private TextField txtSzukaj;
    public ProjectController() { //Utworzeniu pustego konstruktora jest obligatoryjne!
    }
    @FXML
    public void initialize() { //Metoda automatycznie wywoływana przez JavaFX zaraz po wstrzyknięciu
        search4 = null; //wszystkich komponentów. Uwaga! Wszelkie modyfikacje komponentów
        pageNo = 0; //(np. cbPageSizes) trzeba realizować wewnątrz tej metody. Nigdy
        pageSize = 10; //nie używaj do tego celu konstruktora.
        cbPageSizes.getItems().addAll(5, 10, 20, 50, 100);
        cbPageSizes.setValue(pageSize);
    }

    //Zmienne do obsługi stronicowania i wyszukiwania
    private String search4;
    private Integer pageNo;
    private Integer pageSize;
}
