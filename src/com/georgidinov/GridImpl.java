package com.georgidinov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridImpl implements Grid {

    //== fields ==
    private Cell[][] gridGeneration;
    private final List<Integer> greenCellNeighbours =
            new ArrayList<>(Arrays.asList(0, 1, 4, 5, 7, 8));

    private final List<Integer> redCellNeighbours =
            new ArrayList<>(Arrays.asList(0, 1, 2, 4, 5, 7, 8));


    //== constructors ==
    public GridImpl(Cell[][] gridGeneration) {
        this.gridGeneration = gridGeneration;
    }//end of constructor


    //== public methods ==
    @Override
    public int countTheTimesCellColorWasGreen(int row, int col, int numOfGenerations) {
        if (!this.existsInGrid(row, col, this.gridGeneration)) {
            return -1;
        }
        if (numOfGenerations < 0) {
            return -1;
        }

        int cellGreenColorCounter = 0;
        for (int i = 0; i < numOfGenerations; i++) {
            this.gridGeneration = this.nextGeneration(this.gridGeneration);
            if (this.gridGeneration[row][col].getCellColor().equals(CellColor.GREEN)) {
                cellGreenColorCounter++;
            }
        }
        return cellGreenColorCounter;
    }//end of method countTheTimesCellColorWasGreenInNumberOfGenerations


    //== private methods ==
    private Cell[][] nextGeneration(Cell[][] grid) {
        Cell[][] newGrid = new Cell[grid.length][grid[0].length];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {

                Cell currentCell = grid[row][col];

                if (this.canChangeColor(currentCell, grid)) {
                    if (currentCell.getCellColor() == CellColor.GREEN) {
                        currentCell = new Cell(row, col, CellColor.RED);
                    } else if (currentCell.getCellColor() == CellColor.RED) {
                        currentCell = new Cell(row, col, CellColor.GREEN);
                    }
                }
                newGrid[row][col] = currentCell;
            }
        }
        return newGrid;
    }//end of method nextGeneration

    private boolean canChangeColor(Cell cell, Cell[][] grid) {
        List<Cell> neighbourCells = this.getNeighbourCells(cell, grid);

        int greenNeighboursCount = (int) neighbourCells.stream()
                .filter(c -> c.getCellColor() == CellColor.GREEN)
                .count();

        if (cell.getCellColor() == CellColor.GREEN) {
            return this.greenCellNeighbours.contains(greenNeighboursCount);
        } else if (cell.getCellColor() == CellColor.RED) {
            return !this.redCellNeighbours.contains(greenNeighboursCount);
        }
        return false;
    }//end of method canChangeColor

    private List<Cell> getNeighbourCells(Cell cell, Cell[][] grid) {
        List<Cell> neighbourCells = new ArrayList<>();
        int previousRow = cell.getRow() - 1;
        int previousCol = cell.getCol() - 1;
        int nextRow = cell.getRow() + 1;
        int nextCol = cell.getCol() + 1;

        for (int i = previousRow; i <= nextRow; i++) {
            for (int j = previousCol; j <= nextCol; j++) {
                if (this.existsInGrid(i, j, grid)) {
                    Cell currentCell = grid[i][j];
                    if (!this.isTheSameCell(currentCell, cell)) {
                        neighbourCells.add(currentCell);
                    }
                }
            }
        }
        return neighbourCells;
    }//end of method getNeighbourCells


    private boolean existsInGrid(int rowIndex, int colIndex, Cell[][] grid) {
        return rowIndex >= 0 &&
                rowIndex < grid.length &&
                colIndex >= 0 &&
                colIndex < grid[rowIndex].length;
    }//end of method existsInGrid

    private boolean isTheSameCell(Cell currentCell, Cell cell) {
        return currentCell.getRow() == cell.getRow() &&
                currentCell.getCol() == cell.getCol();
    }//end of method isTheSameCell

}//end of class Grid
