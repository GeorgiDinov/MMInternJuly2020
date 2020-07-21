package com.georgidinov;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GridFactory gridFactory = new GridFactoryImpl();
        Grid grid = gridFactory.createGrid();
        System.out.println("Result: " + generationsCounter(grid));
    }//end of main method


    //== private methods ==
    private static int generationsCounter(Grid grid) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().replaceAll("\\s", "");
        String[] rawMethodData = input.split("\\p{Punct}");

        int row = Integer.parseInt(rawMethodData[0]);
        int col = Integer.parseInt(rawMethodData[1]);
        int numGenerations = Integer.parseInt(rawMethodData[2]);
        return grid.countTheTimesCellColorWasGreen(row, col, numGenerations);
    }//end of method generationsCounter

}//end of class Main
