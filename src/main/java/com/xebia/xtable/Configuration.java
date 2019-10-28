package com.xebia.xtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xebia.xtable.Constants.DEFAULT_COLUMN_WIDTH;

public class Configuration {
    private int row;
    private int column;
    private List<Integer> columnWidths;


    private Configuration(Builder builder) {
        if (builder.row <= 0 || builder.column <= 0) {
            throw new IllegalArgumentException("Invalid number of rows or columns");
        }

        if (builder.columnWidths != null) {
            int size = builder.columnWidths.size();
            if (size > 1 && size != builder.column) {
                String message = "Number of column width should be 1 or equal to number of columns";
                throw new IllegalArgumentException(message);
            }
        }

        row = builder.row;
        column = builder.column;
        columnWidths = new ArrayList<>();

        if (builder.columnWidths == null) {
            columnWidths.addAll(insertColumnWidths(DEFAULT_COLUMN_WIDTH));
        } else if (builder.columnWidths.size() == 1) {
            columnWidths.addAll(insertColumnWidths(builder.columnWidths.get(0)));
        } else {
            columnWidths = builder.columnWidths;
        }

    }

    private List<Integer> insertColumnWidths(int val) {
        Integer[] widths = new Integer[this.column];
        Arrays.fill(widths, val);
        return Arrays.asList(widths);
    }

    public int getRow() {
        return row;
    }

    public List<Integer> getColumnWidths() {
        return columnWidths;
    }

    public int getColumn() {
        return column;
    }

    public static final class Builder {
        private int row;
        private int column;
        private List<Integer> columnWidths;

        public Builder() {
        }

        public Builder withRow(int val) {
            row = val;
            return this;
        }

        public Builder withColumn(int val) {
            column = val;
            return this;
        }

        public Builder withColumnWidths(Integer... vals) {
            columnWidths = new ArrayList<>(Arrays.asList(vals));
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
}
