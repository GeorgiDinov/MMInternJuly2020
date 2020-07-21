package com.georgidinov;

public enum CellColor {

    //== enums ==
    RED(0), GREEN(1);


    //== fields ==
    private final int colorId;

    //== constructors ==
    CellColor(int colorId) {
        this.colorId = colorId;
    }//end of constructor

    //== public methods ==
    public int getColorId() {
        return this.colorId;
    }//end of method getColorIdent

}//end of enum CellColor
