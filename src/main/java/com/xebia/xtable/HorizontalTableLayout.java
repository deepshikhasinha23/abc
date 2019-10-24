package com.xebia.xtable;

import java.util.List;

public class HorizontalTableLayout implements TableLayout {


    @Override
    public String create(Configuration configuration, List<List<Cell>> rowsData) {
        StringBuilder generatedTable = new StringBuilder();
        String line = ElementUtils.createHorizontalCellLine(configuration.getColumnWidth());
        for (int i = 0; i < configuration.getRow(); i++) {
            ElementUtils.insertRowLine(line, generatedTable, configuration.getColumn());
            for (int j = 0; j < configuration.getColumn(); j++) {
                ElementUtils.insertCell(generatedTable, rowsData.get(i).get(j));
            }
            ElementUtils.closeCell(generatedTable);
        }
        ElementUtils.insertRowLine(line, generatedTable, configuration.getColumn());
        return generatedTable.toString();
    }
}
