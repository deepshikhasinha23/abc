package com.xebia.xtable;

import com.xebia.xtable.renderer.TableRenderer;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private int row;
    private int column;
    private List<List<Cell>> tableData;
    private int columnWidth;
    private StringBuilder generatedTable;
    private LayoutManager layoutManager;
    private int numberOfRowsWithData;

    public Table(int row, int column) {
        if (row <= 0 || column <= 0)
            throw new IllegalArgumentException( "Invalid number of rows or columns" );
        this.row = row;
        this.column = column;
        this.columnWidth = 10;
        numberOfRowsWithData=0;
        this.layoutManager = new LayoutManager();
        tableData = new ArrayList<>();
        generatedTable = new StringBuilder();
        fillData();
    }

    public String create() {
        generatedTable.setLength( 0 );
        layoutManager.createColumnHorizontalLine( this.columnWidth );
        for (int i = 0; i < row; i++) {
            layoutManager.insertRowLine( generatedTable, column );
            for (int j = 0; j < column; j++) {
                layoutManager.insertCell( generatedTable, tableData.get( i ).get( j ) );
            }
            layoutManager.endCell( generatedTable );
        }
        layoutManager.insertRowLine( generatedTable, column );
        return generatedTable.toString();
    }

    public void render() {
        render( TableRenderer.getConsoleBasedRenderer() );
    }

    public void render(TableRenderer tableRenderer) {
        tableRenderer.render( generatedTable.toString() );
    }


    private void fillData() {
        for (int i = 0; i < row; i++) {
            List<Cell> rowData = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                rowData.add( new Cell( "" ) );
            }
            tableData.add( rowData );
        }
    }

    public void addNumberOfRows(int row) {
        this.row += row;
    }

    public void addNumberOfColumns(int column) {
        this.column += column;
    }

    public void reduceNumberOfRows(int row) {
        if (row >= this.row) {
            throw new IllegalArgumentException( "The input can't be equal or greater than current row count." );
        }
        this.row -= row;
    }

    public void reduceNumberOfColumns(int column) {
        if (column >= this.column) {
            throw new IllegalArgumentException( "The input can't be equal or greater than current column count." );
        }
        this.column -= column;
    }

    public String shape() {
        return String.join( "", "(" + row + "*" + column + ")" );
    }


    public String addHeader(String... headers) {
        if (headers.length != this.column)
            throw new IllegalArgumentException( "Number of headers should be equal to number of columns." );
        List<Cell> header = new ArrayList<>();
        for (String s : headers) {
            header.add( new Cell( s, true ) );
        }
        tableData.add( 0, header );
        numberOfRowsWithData++;
        return create();
    }

    public String addDataInRow(String... rowData) {
        if (rowData.length != this.column)
            throw new IllegalArgumentException("Number of data in a row should be equal to number of columns." );

        if (numberOfRowsWithData==this.row){
            throw new IllegalStateException("Table is full,create  another row" );
        }
        List<Cell> row = new ArrayList<>();
        for (String s : rowData) {
            row.add( new Cell( s, false ) );
        }
        tableData.add( numberOfRowsWithData,row );
        numberOfRowsWithData++;
        return create();
    }
}