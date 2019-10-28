package com.xebia.xtable;

import static com.xebia.xtable.Constants.EMPTY_DATA;

class Cell {
    private String data;
    private int width;
    private boolean isHeader;

    static Cell createEmpty(int width) {
        return new Cell(EMPTY_DATA,  width,false);
    }
    static Cell createWithData(String s, int width, boolean isHeader) {
        return new Cell(s,  width, isHeader);
    }
    Cell(String data, int width, boolean isHeader) {
        this.data = data;
        this.width = width;
        this.isHeader=isHeader;
    }

    String getData() {
        return data;
    }

    int getWidth() {
        return width;
    }

    void setWidth(Integer width) {
        this.width=width;
    }

    public boolean isHeader() {
        return isHeader;
    }
}