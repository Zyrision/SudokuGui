package com.example.sudoku_gui;

import javafx.scene.control.TextField;

import static java.lang.Integer.parseInt;

public class Sudo {

    static int[][] getSudo(TextField[][] tf){
        int[][] grid = new int[9][9];

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                grid[i][j] = parseInt(tf[i][j].getText());
                if (parseInt(tf[i][j].getText()) != 0) tf[i][j].setStyle("-fx-text-fill: green;");
            }
        }
        return grid;
    }


    /**
     * Überprüft die Row, Column und die 3x3 Box ob die gegebene Zahl bereits vorhanden ist
     * @param grid
     * @param row
     * @param column
     * @param nummer
     * @return
     */
    static boolean checkPos(int[][] grid, int row, int column,	int nummer){
        // row check
        for (int x = 0; x <= 8; x++)
            if (grid[row][x] == nummer)
                return false;
        // column check
        for (int x = 0; x <= 8; x++)
            if (grid[x][column] == nummer)
                return false;
		/*
		3x3 check
		Tafel Beispiel row = 3 und col = 5
		ergibt nach der Umstellung Koordinaten == 3/3
		geht dann das 3x3 Feld von row = 3 und col = 3 durch und prüft die Zahl
		 */
        int startRow = row - row % 3,
                startCol = column - column % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i + startRow][j + startCol] == nummer)
                    return false;

        return true;
    }

    /**
     * Sudoku übergeben und Start Row/Column
     * @param grid
     * @param row
     * @param column
     * @return
     */
    static boolean loeser(int grid[][], int row, int column){
		/*
		überprüft ob die Funktion bereits am ende vom Sudoku Feld angekommen ist
		Wenn True dann return "True" um den Aufruf zu beenden
		 */
        if (row == 9 - 1 && column == 9)
            return true;

        if (column == 9) {
            row++;
            column = 0;
        }

		/*
		Check ob Zahl vorhanden wenn ja springen wir weiter
		 */
        if (grid[row][column] != 0)
            return loeser(grid, row, column + 1);

        for (int num = 1; num < 10; num++) {

            //Versucht eine nummer "num" zu platzieren
            if (checkPos(grid, row, column, num)) {

                //wenn möglich wird die Nummer in dem aktuellen Feld abgelegt
                grid[row][column] = num;

                //geht in die nächste Feld und fängt von vorne an
                if (loeser(grid, row, column + 1))
                    return true;
            }
            grid[row][column] = 0;
        }
        return false;
    }
}