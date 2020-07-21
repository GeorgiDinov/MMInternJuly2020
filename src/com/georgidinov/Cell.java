package com.georgidinov;

public class Cell {

    //== fields ==
    private final int row;
    private final int col;
    private CellColor cellColor;

    //== constructors ==
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellColor = null;
    }//end of constructor

    public Cell(int row, int col, CellColor cellColor) {
        this.row = row;
        this.col = col;
        this.cellColor = cellColor;
    }//end of constructor


    //== public methods ==
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellColor getCellColor() {
        return cellColor;
    }

    public void setCellColor(CellColor cellColor) {
        this.cellColor = cellColor;
    }

}//end of class Cell
