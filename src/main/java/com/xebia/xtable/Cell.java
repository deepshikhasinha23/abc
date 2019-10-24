package com.xebia.xtable;

import static com.xebia.xtable.Constants.EMPTY_DATA;

class Cell {
    private String data;
    private boolean isHeader;

    Cell(String data) {
        this(data, false);
    }

    Cell(String data, boolean isHeader) {
        this.data = data;
        this.isHeader = isHeader;
    }

    static Cell createWithData(String s) {
        return new Cell(s, false);
    }

    static Cell createEmpty() {
        return new Cell(EMPTY_DATA);
    }

    public boolean isHeader() {
        return isHeader;
    }

    String getData() {
        return data;
    }
}