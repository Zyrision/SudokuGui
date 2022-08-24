package com.example.sudoku_gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javax.swing.*;
import java.util.function.UnaryOperator;

import static com.example.sudoku_gui.Sudo.getSudo;
import static com.example.sudoku_gui.Sudo.loeser;

public class HelloController {
    @FXML
    private TextArea welcomeText;

    @FXML
    private GridPane gp_Sudo;

    @FXML
    private AnchorPane body;

    private TextField[][] txt;

    @FXML
    public void initialize() {
        welcomeText.setText("Trage ein Sudoku in das Raster ein und drücke den 'Lösen' Button\nRot angezeigte Zahlen sind eingetragene Zahlen und Grün die gelösten.");
        txt = new TextField[9][9];
        int count = 0;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                txt[i][j] = new TextField();
                txt[i][j].setAlignment(Pos.CENTER);
                txt[i][j].setId(String.valueOf(i));
                txt[i][j].setText("0");
                // gefunden im Inet hab ich noch nie mit gearbeitet daher ka wie das genau geht :-D
                UnaryOperator<TextFormatter.Change> integerFilter = change -> {
                    String input = change.getText();
                    if (input.matches("[0-9]*")) {
                        return change;
                    }
                    return null;
                };
                txt[i][j].setTextFormatter(new TextFormatter<>(integerFilter));
                count++;
                gp_Sudo.add(txt[i][j],j,i,1,1);
            }
        }
    }
    @FXML
    public void loese_Sudo(ActionEvent actionEvent) {

        int[][] grid = getSudo(txt);
        if (loeser(grid,0,0)){
            for (int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    txt[i][j].setText(String.valueOf(grid[i][j]));
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Keine Lösung möglich!");
        }
    }

    public void reset(ActionEvent actionEvent) {
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                txt[i][j].setText("0");
            }
        }
    }
}