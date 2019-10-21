package com.xebia.xtable;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println( "Enter number of rows and columns" );
        Scanner input = new Scanner( System.in );
        int numberOfRows = input.nextInt();
        int numberOfColumns = input.nextInt();
        Table table = new Table( numberOfRows, numberOfColumns );
        table.create();
        table.render();
        System.out.println( "Enter the headers " );
        String[] headers = new String[numberOfColumns];
        for (int i = 0; i < numberOfColumns; i++) {
            headers[i] = input.next();
        }
        table.addHeader( headers );
        table.render();
        System.out.println( "Enter the number of data rows" );
        int numberOfDataRows=input.nextInt();
        while (numberOfDataRows>0){
            System.out.println( "Enter the data" );
            String[] dataRow = new String[numberOfColumns];
            for (int i = 0; i < numberOfColumns; i++) {
                dataRow[i] = input.next();
            }
            table.addDataInRow( dataRow );
            table.render();
            numberOfDataRows--;
        }
    }
}
