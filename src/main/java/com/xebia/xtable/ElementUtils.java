package com.xebia.xtable;

import java.util.Collections;

import static com.xebia.xtable.Constants.CONNECTOR;
import static com.xebia.xtable.Constants.HORIZONTAL_LINE_BUILDER;
import static com.xebia.xtable.Constants.VERTICAL_SEPARATOR;
import static com.xebia.xtable.Constants.START_POSITION;
import static com.xebia.xtable.Constants.TRUNCATED_DATA_SYMBOL;
import static com.xebia.xtable.Constants.SINGLE_SPACE;

abstract class ElementUtils {

    static String createHorizontalCellLine(int width) {
        return CONNECTOR + String.join("", Collections.nCopies(width, HORIZONTAL_LINE_BUILDER));

    }

    static String insertCell(StringBuilder builder, Cell cell) {
        builder.append(VERTICAL_SEPARATOR).append(truncateData(cell));
        addPaddingRight(builder, cell.getWidth() - cell.getData().length());
        return builder.toString();
    }

    static String truncateData(Cell cell) {
        String cellData = cell.getData();
        if (cellData.length() > cell.getWidth()) {
            String truncatedString = cellData.substring(START_POSITION, (cell.getWidth() - 3));
            return String.join("", truncatedString, TRUNCATED_DATA_SYMBOL);
        }
        return cellData;
    }

    static String addPaddingRight(StringBuilder builder, int i) {
        for (int j = 0; j < i; j++) {
            builder.append(SINGLE_SPACE);
        }
        return builder.toString();
    }

    static String closeCell(StringBuilder builder) {
        builder.append(VERTICAL_SEPARATOR).append("\n");
        return builder.toString();
    }

    static String insertRowLine(Configuration configuration, StringBuilder generatedTable,
                                LayoutOptions layoutOptions) {
        int size;
        if (layoutOptions == LayoutOptions.HORIZONTAL) {
            size = configuration.getColumn();
        } else {
            size = configuration.getRow();
        }
        for (int j = 0; j < size; j++) {
            Integer width = configuration.getColumnWidths().get(j);
            String line = ElementUtils.createHorizontalCellLine(width);
            generatedTable.append(line);
        }
        generatedTable.append(CONNECTOR + "\n");
        return generatedTable.toString();
    }

}
