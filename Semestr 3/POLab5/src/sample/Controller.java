package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;


    public void registerUser() {
        try {
            String name = nameField.getText();
            String age = ageField.getText();
            Register.checkName(name);
            Register.checkAge(age);

            nameField.clear();
            ageField.clear();

        }catch (NumberInNameException e){
           Alert blad = new Alert(Alert.AlertType.ERROR, e.getMessage());
           blad.setHeaderText("Wystąpił problem w polu imię, wpisz je ponownie");
           blad.showAndWait();
           nameField.clear();

        }catch (AgeException ageException) {
            Alert blad = new Alert(Alert.AlertType.ERROR, ageException.getMessage());
            blad.setHeaderText("Wystąpiłe problem w polu wiek, wpisz go ponownie.");
            blad.showAndWait();
            ageField.clear();
        }

    }
}