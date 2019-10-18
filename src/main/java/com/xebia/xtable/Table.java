package com.xebia.xtable;

import com.xebia.xtable.renderer.TableRenderer;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private int row;
    private int column;
    private List<List<String>> tableData;
    private int columnWidth;
    private StringBuilder generatedTable;
    private LayoutManager layoutManager;

    public Table(int row, int column) {
        if (row <= 0 || column <= 0)
            throw new IllegalArgumentException("Invalid number of rows or columns");
        this.row = row;
        this.column = column;
        this.columnWidth = 10;
        this.layoutManager = new LayoutManager();
        tableData = new ArrayList<>();
        generatedTable = new StringBuilder();
        fillData();

    }

    public String create() {
        layoutManager.createColumnHorizontalLine(this.columnWidth);
        for (int i = 0; i < row; i++) {
            layoutManager.insertRowLine(generatedTable, column);
            for (int j = 0; j < column; j++) {
                Cell cell = new Cell(tableData.get(i).get(j));
                layoutManager.insertCell(generatedTable, cell);
            }
            layoutManager.endCell(generatedTable);
        }
        layoutManager.insertRowLine(generatedTable, column);
        return generatedTable.toString();
    }

    public void render() {
        render(TableRenderer.getConsoleBasedRenderer());
    }

    public void render(TableRenderer tableRenderer) {
        tableRenderer.render(generatedTable.toString());
    }


    private void fillData() {
        for (int i = 0; i < row; i++) {
            List<String> rowData = new ArrayList<>();
            for (int j = 0; j < column; j++) {
                rowData.add("          ");
            }
            tableData.add(rowData);
        }
    }

    public void addNumberOfRows(int row) {
        this.row += row;
    }

    public void addNumberOfColumns(int column) {
        this.column += column;
    }

    public void reduceNumberOfRows(int row) {
        if (!isValidTable(row, this.row)) {
            throw new IllegalArgumentException("Argument should be lesser than current number of rows");
        }
        this.row -= row;
    }

    public void reduceNumberOfColumns(int column) {
        if (!(isValidTable(column, this.column))) {
            throw new IllegalArgumentException("Argument should be lesser than current number of columns");
        }
        this.column -= column;
    }

    public String shape() {
        return String.join("", "(" + row + "*" + column + ")");
    }


    private boolean isValidTable(int givenData, int currentData) {
        return currentData - givenData >= 0;
    }
}