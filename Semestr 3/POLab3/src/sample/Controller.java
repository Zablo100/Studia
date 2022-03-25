package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.util.*;

public class Controller {
    List<Button> buttons = new ArrayList<Button>();

    @FXML
    private Label winnerLable;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;


    int clicks = 0;
    int gameState = 0;
    public void click(ActionEvent e) {
        winnerLable.setAlignment(Pos.CENTER);
        clicks++;
        Button b = (Button) e.getTarget();
        b.setStyle("-fx-font-size: 45");
        if (gameState == 0) {
            if (clicks % 2 == 0) {
                winnerLable.setText("Runda gracza O!");
                b.setText("x");
                b.setDisable(true);
            } else {
                winnerLable.setText("Runda gracza X!");
                b.setText("o");
                b.setDisable(true);
            }
            buttons.add(b);
            remis();
            chceckWin();
        }
    }
    public void stopGame(){
        gameState = 1;
    }

    public void gameOver(int player){
        if(player == 1){
            winnerLable.setText("Wygrał gracz O");
            stopGame();
        }else if(player == 2){
            winnerLable.setText("Wygrał gracz X");
            stopGame();
        }
    }
    public void chceckWin(){
        if (b1.getText().equals("o") && b2.getText().equals("o") && b3.getText().equals("o")){
            gameOver(1);
        }
        if (b4.getText().equals("o") && b5.getText().equals("o") && b6.getText().equals("o")){
            gameOver(1);
        }
        if (b7.getText().equals("o") && b8.getText().equals("o") && b9.getText().equals("o")){
            gameOver(1);
        }
        if (b1.getText().equals("o") && b5.getText().equals("o") && b9.getText().equals("o")){
            gameOver(1);
        }
        if (b3.getText().equals("o") && b5.getText().equals("o") && b7.getText().equals("o")){
            gameOver(1);
        }
        if (b1.getText().equals("o") && b4.getText().equals("o") && b7.getText().equals("o")){
            gameOver(1);
        }
        if (b2.getText().equals("o") && b5.getText().equals("o") && b8.getText().equals("o")){
            gameOver(1);
        }
        if (b3.getText().equals("o") && b6.getText().equals("o") && b9.getText().equals("o")){
            gameOver(1);
        }


        if (b1.getText().equals("x") && b2.getText().equals("x") && b3.getText().equals("x")){
            gameOver(2);
        }
        if (b4.getText().equals("x") && b5.getText().equals("x") && b6.getText().equals("x")){
            gameOver(2);
        }
        if (b7.getText().equals("x") && b8.getText().equals("x") && b9.getText().equals("x")){
            gameOver(2);
        }
        if (b1.getText().equals("x") && b5.getText().equals("x") && b9.getText().equals("x")){
            gameOver(2);
        }
        if (b3.getText().equals("x") && b5.getText().equals("x") && b7.getText().equals("x")){
            gameOver(2);
        }
        if (b1.getText().equals("x") && b4.getText().equals("x") && b7.getText().equals("x")){
            gameOver(2);
        }
        if (b2.getText().equals("x") && b5.getText().equals("x") && b8.getText().equals("x")){
            gameOver(2);
        }
        if (b3.getText().equals("x") && b6.getText().equals("x") && b9.getText().equals("x")){
            gameOver(2);
        }

    }
    public void remis(){
        if(clicks == 9){
            winnerLable.setText("Remis");
        }
    }

    public void restartGame(){
        clicks = 0;
        gameState = 0;
        winnerLable.setText("Runda gracza O!");

        for(Button b: buttons){
            b.setText("");
            b.setDisable(false);
        }

    }

}