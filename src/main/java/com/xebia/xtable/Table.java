package com.xebia.xtable;

import com.xebia.xtable.renderer.TableRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.xebia.xtable.Constants.START_POSITION;

public class Table {
    private Configuration configuration;
    private List<List<Cell>> rowsData;
    private String generatedTable;
    private int numberOfRowsWithData;
    private TableLayout tableLayout;

    public Table(Configuration configuration) {
        numberOfRowsWithData = 0;
        rowsData = new ArrayList<>();
        generatedTable = "";
        this.configuration=configuration;
        tableLayout = new HorizontalTableLayout();
        fillTableWithEmptyData();
    }

    public String create() {
        return create(LayoutOptions.HORIZONTAL);
    }

    public String create(LayoutOptions layoutOption) {
        if (layoutOption == LayoutOptions.HORIZONTAL) {
            tableLayout = new HorizontalTableLayout();
        } else {
            tableLayout = new VerticalTableLayout();
        }
        generatedTable = tableLayout.create(configuration, rowsData);
        return generatedTable;
    }

    public void render() {
        render(TableRenderer.getConsoleBasedRenderer());
    }

    public void render(TableRenderer tableRenderer) {
        tableRenderer.render(generatedTable);
    }


    private void fillTableWithEmptyData() {
        for (int i = 0; i < configuration.getRow(); i++) {
            List<Cell> rowData = Collections.nCopies(configuration.getColumn(), Cell.createEmpty());
            rowsData.add(rowData);
        }
    }

    public String shape() {
        return String.join("", "(" + configuration.getRow() + "*" + configuration.getColumn() + ")");
    }


    public void addHeader(String... headers) {
        if (headers.length != this.configuration.getColumn())
            throw new IllegalArgumentException("Number of headers should be equal to number of columns.");
        List<Cell> header = new ArrayList<>();
        for (String s : headers) {
            header.add(new Cell(s, true));
        }
        rowsData.add(START_POSITION, header);
        numberOfRowsWithData++;
    }

    public void addDataInRow(String... rowData) {
        if (rowData.length != this.configuration.getColumn())
            throw new IllegalArgumentException("Number of data in a row should be equal to number of columns.");

        if (numberOfRowsWithData == this.configuration.getRow()) {
            throw new IllegalStateException("Table is full");
        }
        List<Cell> row = new ArrayList<>();
        for (String s : rowData) {
            row.add(Cell.createWithData(s));
        }
        rowsData.add(numberOfRowsWithData, row);
        numberOfRowsWithData++;
    }
}