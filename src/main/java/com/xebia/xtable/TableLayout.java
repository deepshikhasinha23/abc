package com.xebia.xtable;


import java.util.List;

public interface TableLayout {
    String create(Configuration configuration, List<List<Cell>> rowsData);
}
