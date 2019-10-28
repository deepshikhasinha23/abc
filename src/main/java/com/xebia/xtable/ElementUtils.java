package com.xebia.xtable;

import java.util.Collections;

import static com.xebia.xtable.Constants.*;

abstract class ElementUtils {

    static String createHorizontalCellLine(int width) {
        return CONNECTOR + String.join( "", Collections.nCopies( width, HORIZONTAL_LINE_BUILDER ) );

    }

    static String insertCell(StringBuilder builder, Cell cell) {
        builder.append( VERTICAL_SEPARATOR ).append( truncateData( cell ) );
        addPaddingRight( builder, cell.getWidth() - cell.getData().length() );
        return builder.toString();
    }

    static String truncateData(Cell cell) {
        if (cell.getData().length() > cell.getWidth()) {
            return String.join( "", cell.getData().substring( START_POSITION, (cell.getWidth() - 3) ), TRUNCATED_DATA_SYMBOL );
        }
        return cell.getData();
    }

    static String addPaddingRight(StringBuilder builder, int i) {
        for (int j = 0; j < i; j++) {
            builder.append( SINGLE_SPACE );
        }
        return builder.toString();
    }

    static String closeCell(StringBuilder builder) {
        builder.append( VERTICAL_SEPARATOR ).append( "\n" );
        return builder.toString();
    }

    static String insertRowLine(Configuration configuration, StringBuilder generatedTable, LayoutOptions layoutOptions) {
        int size = 0;
        if (layoutOptions == LayoutOptions.HORIZONTAL) {
            size = configuration.getColumn();
        } else {
            size = configuration.getRow();
        }
        for (int j = 0; j < size; j++) {
            String line = ElementUtils.createHorizontalCellLine( configuration.getColumnWidths().get( j ) );
            generatedTable.append( line );
        }
        generatedTable.append( CONNECTOR + "\n" );
        return generatedTable.toString();
    }

}
