package com.georgidinov;

import java.util.Scanner;

public class GridFactoryImpl implements GridFactory {

    //== public methods ==
    @Override
    public Grid createGrid() {
        String[] gridDimensions = this.getRawData().split("\\p{Punct}");
        int width = Integer.parseInt(gridDimensions[0]);
        int height = Integer.parseInt(gridDimensions[1]);
        Cell[][] cells = this.initializeGridCells(width, height);
        this.populateGridCells(cells);
        return new GridImpl(cells);
    }//end of method createGrid


    //== private methods ==
    private boolean isValidRatio(int width, int height) {
        return width <= height;
    }//end of method isValidRatio

    private boolean isInValidRange(int gridSide) {
        return gridSide >= 0 && gridSide < 1_000;
    }//end of method isInValidRange

    private Cell[][] initializeGridCells(int width, int height) {
        if (this.isInValidRange(width) && this.isInValidRange(height)) {
            if (this.isValidRatio(width, height)) {
                return new Cell[width][height];
            }
        }
        return null;
    }//end of method initializeGridCells

    private void populateGridCells(Cell[][] cells) {
        if (cells != null) {
            for (int row = 0; row < cells.length; row++) {

                char[] gridLine = this.getRawData().toCharArray();

                for (int col = 0; col < cells[row].length; col++) {

                    Cell currentCell = new Cell(row, col);
                    cells[row][col] = currentCell;
                    int colorId = Integer.parseInt(String.valueOf(gridLine[col]));

                    if (colorId == 0) {
                        currentCell.setCellColor(CellColor.RED);
                    } else if (colorId == 1) {
                        currentCell.setCellColor(CellColor.GREEN);
                    }
                }
            }
        } else {
            System.out.println("Grid Cells Are Not Initialized!");
        }
    }//end of method populateGridCells

    private String getRawData() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().replaceAll("\\s", "");
    }//end of method getRawData

}//end of class GridBuilder
