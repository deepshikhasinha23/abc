package com.xebia.xtable;

import java.util.List;

import static com.xebia.xtable.ElementUtils.insertCell;
import static com.xebia.xtable.ElementUtils.insertRowLine;
import static com.xebia.xtable.ElementUtils.closeCell;

public class HorizontalTableLayout implements TableLayout {

    @Override
    public String create(Configuration configuration, List<List<Cell>> rowsData) {
        StringBuilder generatedTable = new StringBuilder();
        int length = configuration.getColumn();
        for (int i = 0; i < configuration.getRow(); i++) {
            insertRowLine(configuration, generatedTable, LayoutOptions.HORIZONTAL);
            for (int j = 0; j < length; j++) {
                insertCell(generatedTable, rowsData.get(i).get(j));
            }
            closeCell(generatedTable);
        }
        insertRowLine(configuration, generatedTable, LayoutOptions.HORIZONTAL);
        return generatedTable.toString();
    }

}
