package com.xebia.xtable;

import java.util.Collections;
import java.util.List;

import static com.xebia.xtable.ElementUtils.*;

public class VerticalTableLayout implements TableLayout {

    @Override
    public String create(Configuration configuration, List<List<Cell>> rowsData) {
        StringBuilder generatedTable = new StringBuilder();
        Integer max = Collections.max( configuration.getColumnWidths() );
        setCellWidthMax( max, rowsData );
        updateColumnWidths( configuration, max );
        for (int i = 0; i < configuration.getColumn(); i++) {
            insertRowLine( configuration, generatedTable, LayoutOptions.VERTICAL );
            for (int j = 0; j < configuration.getRow(); j++) {
                insertCell( generatedTable, rowsData.get( j ).get( i ) );
            }
            closeCell( generatedTable );
        }
        insertRowLine( configuration, generatedTable, LayoutOptions.VERTICAL );
        return generatedTable.toString();
    }

    private void updateColumnWidths(Configuration configuration, int max) {
        for (int i = 0; i < configuration.getRow(); i++) {
            configuration.getColumnWidths().add( i, max );
        }
    }

    private void setCellWidthMax(Integer max, List<List<Cell>> rowsData) {
        for (List<Cell> cells : rowsData) {
            for (Cell cell : cells) {
                cell.setWidth( max );
            }
        }
    }
}

