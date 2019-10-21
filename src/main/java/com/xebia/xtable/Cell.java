package com.xebia.xtable;

class Cell {
    private String cellData;
    private boolean isHeader;

    public Cell(String cellData) {
        this( cellData, false );
    }

    public Cell(String cellData, boolean isHeader) {
        this.cellData = cellData;
        this.isHeader = isHeader;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public String getCellData() {
        return cellData;
    }
}