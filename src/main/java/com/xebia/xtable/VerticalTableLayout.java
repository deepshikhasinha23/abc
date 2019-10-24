package com.xebia.xtable;

import java.util.List;

public class VerticalTableLayout implements TableLayout {

    @Override
    public String create(Configuration configuration, List<List<Cell>> rowsData) {
        StringBuilder generatedTable = new StringBuilder();
        String line = ElementUtils.createHorizontalCellLine(configuration.getColumnWidth());
        for (int i = 0; i < configuration.getColumn(); i++) {
            ElementUtils.insertRowLine(line, generatedTable, configuration.getRow());
            for (int j = 0; j < configuration.getRow(); j++) {
                ElementUtils.insertCell(generatedTable, rowsData.get(j).get(i));
            }
            ElementUtils.closeCell(generatedTable);
        }
        ElementUtils.insertRowLine(line, generatedTable, configuration.getRow());
        return generatedTable.toString();
    }
}

