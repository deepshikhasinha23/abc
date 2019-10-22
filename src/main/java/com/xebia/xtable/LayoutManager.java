package com.xebia.xtable;

import java.util.Collections;

import static com.xebia.xtable.Constants.*;

class LayoutManager {
    private String rowLine = "";

    String createHorizontalCellLine(int width) {
        rowLine = CONNECTOR + String.join( "", Collections.nCopies( width, HORIZONTAL_LINE_BUILDER ) );
        return rowLine;
    }

    String insertRowLine(StringBuilder builder, int length) {
        for (int j = 0; j < length; j++) {
            builder.append( rowLine );
        }
        builder.append( CONNECTOR ).append( "\n" );
        return builder.toString();
    }

    String insertCell(StringBuilder builder, Cell cell) {
        builder.append( VERTICAL_SEPARATOR ).append( cell.getData() );
        addPaddingRight( builder, 10 - cell.getData().length() );
        return builder.toString();
    }

    String addPaddingRight(StringBuilder builder, int i) {
        for (int j = 0; j < i; j++) {
            builder.append( SINGLE_SPACE );
        }
        return builder.toString();
    }

    String closeCell(StringBuilder builder) {
        builder.append( VERTICAL_SEPARATOR ).append( "\n" );
        return builder.toString();
    }
}
