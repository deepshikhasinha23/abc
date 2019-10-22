package com.xebia.xtable;

class Cell {
    private String data;
    private boolean isHeader;

    Cell(String data) {
        this( data, false );
    }

    Cell(String data, boolean isHeader) {
        this.data = data;
        this.isHeader = isHeader;
    }

    static Cell dataRow(String s) {
        return new Cell(  s,false);
    }

    public boolean isHeader() {
        return isHeader;
    }

    String getData() {
        return data;
    }
}