package com.xebia.xtable;

import com.xebia.xtable.renderer.TableRenderer;

import java.util.ArrayList;
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
        this.configuration = configuration;
        tableLayout = new HorizontalTableLayout();
        fillTableWithEmptyData();
    }

    public static Table getEmptyTable(int row, int column) {
        return new Table(new Configuration.Builder().withRow(row).withColumn(column).build());
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
            List<Cell> rowData = new ArrayList<>();
            for (int j = 0; j < configuration.getColumn(); j++) {
                rowData.add(Cell.createEmpty(configuration.getColumnWidths().get(j)));
            }
            rowsData.add(rowData);
        }
    }

    public String shape() {
        int row = configuration.getRow();
        int column = configuration.getColumn();
        return String.join("", "(" + row + "*" + column + ")");
    }


    public void addHeader(String... headers) {
        if (headers.length != this.configuration.getColumn()) {
            String message = "Number of headers should be equal to number of columns.";
            throw new IllegalArgumentException(message);
        }

        List<Cell> header = new ArrayList<>();
        for (int i = 0; i < configuration.getColumn(); i++) {
            Integer width = configuration.getColumnWidths().get(i);
            header.add(Cell.createWithData(headers[i], width, true));
        }
        rowsData.add(START_POSITION, header);
        numberOfRowsWithData++;
    }

    public Table addDataInRow(String... rowData) {
        if (rowData.length != this.configuration.getColumn()) {
            String message = "Number of data in a row should be equal to number of columns.";
            throw new IllegalArgumentException(message);
        }

        if (numberOfRowsWithData == this.configuration.getRow()) {
            throw new IllegalStateException("Table is full");
        }

        List<Cell> row = new ArrayList<>();
        for (int i = 0; i < configuration.getColumn(); i++) {
            row.add(Cell.createWithData(rowData[i], configuration.getColumnWidths().get(i), false));
        }
        rowsData.add(numberOfRowsWithData, row);
        numberOfRowsWithData++;
        return this;
    }
}
