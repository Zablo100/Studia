import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class Game extends JFrame {
    int proby, diament;
    JLabel napis = new JLabel();
    JTextArea tekst = new JTextArea();

    int losowanie() {
        Random rng = new Random();
        return rng.nextInt(2);
    }
    void wyswietlNapis(){
        napis.setText("Próby: " + (proby) + " Diamenty: " + (diament));
    }
    void wyswietlTekst(String wylosowane){
        tekst.setText("Wylosowałeś " + wylosowane + "\n" + tekst.getText());
        koniec();
    }
    void koniec(){
        if (proby == 20){
            tekst.setText("Gra skończona! Udało Ci się zdobyć " + (diament) + " diamentów\n");
            proby = 0;
            diament = 0;
        }
    }
    Game() {
        System.out.print("Wpisz nazwe gracza:");
        String gracz = new Scanner(System.in).next();

        setSize(500, 500);
        setTitle("Gra: " + gracz);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2, 10, 10));
        setResizable(false);

        JButton przycisk1 = new JButton("Kliknij tutaj");
        JButton przycisk2 = new JButton("A może tutaj?");

        napis.setText("");
        napis.setHorizontalAlignment(SwingConstants.CENTER);
        tekst.setFocusable(false);
        tekst.setLineWrap(true);

        przycisk1.setFocusable(false);
        przycisk2.setFocusable(false);

        add(przycisk1);
        add(przycisk2);
        add(napis);
        add(tekst);

        przycisk1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proby++;
                int los = losowanie();
                if (los == 1){
                    diament++;
                    wyswietlNapis();
                    wyswietlTekst("diament!");
                }else{
                    wyswietlNapis();
                    wyswietlTekst("bombe!");
                }
            }
        });
        przycisk2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proby++;
                int los = losowanie();
                if (1 - los == 1) {
                    diament++;
                    wyswietlNapis();
                    wyswietlTekst("diament!");
                } else {
                    wyswietlNapis();
                    wyswietlTekst("bombe!");
                }
            }
        });

        setVisible(true);
    }
}
