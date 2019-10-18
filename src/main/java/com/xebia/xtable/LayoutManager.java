package com.xebia.xtable;

import java.util.Collections;

class LayoutManager {
    private final String verticalSeparator = "|";
    private final String connector = "+";
    private String rowLine = "";

    String createColumnHorizontalLine(int width) {
        String horizontalLine = "-";
        rowLine = connector + String.join("", Collections.nCopies(width, horizontalLine));
        return rowLine;
    }

    String insertRowLine(StringBuilder builder, int length) {
        for (int j = 0; j < length; j++) {
            builder.append(rowLine);
        }
        builder.append(connector).append("\n");
        return builder.toString();
    }

    String insertCell(StringBuilder builder, Cell cell) {
        builder.append(verticalSeparator).append(cell.getCellData());
        return builder.toString();
    }

    String endCell(StringBuilder builder) {
        builder.append(verticalSeparator).append("\n");
        return builder.toString();
    }
}
