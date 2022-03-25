package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.*;

public class Controller {

    @FXML
    private TextArea Text;

    public void zapisz(){ // Metoda zapisu do tekstu
        try{
            BufferedWriter buffWirter = new BufferedWriter(new FileWriter("plik.txt")); // Buffer Writer bo chcemy coś zapisać do pliku
            buffWirter.write(Text.getText()); // Pobiera tekst z naszego programu i zapisuje w pliku
            buffWirter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void wczytaj(){ // metoda wczytania pliku
        Text.clear();
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader("plik.txt")); // Buffor Reader bo chcemy odczytać coś z pliku
            String temp; // Pomocniacza zmienna do przechowywania tekstu
            while ((temp = buffReader.readLine()) != null) {
                Text.appendText(temp + "\n"); // Dołączamy do interfejsu graficznego teksty pobrany z pliku
            }
            buffReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
