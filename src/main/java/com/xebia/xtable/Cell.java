package com.xebia.xtable;

import static com.xebia.xtable.Constants.EMPTY_DATA;

class Cell {
    private String data;
    private boolean isHeader;
    private int width;

    Cell(String data, boolean isHeader, int width) {
        this.data = data;
        this.isHeader = isHeader;
        this.width = width;
    }

    static Cell createWithData(String s, int width) {
        return new Cell(s, false, width);
    }

    static Cell createEmpty(int width) {
        return new Cell(EMPTY_DATA, false, width);
    }

    public boolean isHeader() {
        return isHeader;
    }

    String getData() {
        return data;
    }

    public int getWidth() {
        return width;
    }
}