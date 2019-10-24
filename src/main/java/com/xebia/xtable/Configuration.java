package com.xebia.xtable;

import static com.xebia.xtable.Constants.DEFAULT_COLUMN_WIDTH;

public class Configuration {
    private int row;
    private int column;
    private int columnWidth;

    private Configuration(Builder builder) {
        if (builder.row <= 0 || builder.column <= 0)
            throw new IllegalArgumentException("Invalid number of rows or columns");
        row = builder.row;
        column = builder.column;
        if (builder.columnWidth == 0) {
            columnWidth = DEFAULT_COLUMN_WIDTH;
        } else {
            columnWidth = builder.columnWidth;
        }
    }

    public static final class Builder {
        private int row;
        private int column;
        private int columnWidth;

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

        public Builder withColumnWidth(int val) {
            columnWidth = val;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getColumnWidth() {
        return columnWidth;
    }
}
