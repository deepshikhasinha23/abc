package com.xebia.xtable;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class LayoutManagerTest {
    @Test
    public void should_be_able_to_create_a_horizontal_line_of_width_5() {
        LayoutManager layoutManager = new LayoutManager();
        String columnHorizontalLine = layoutManager.createColumnHorizontalLine(5);
        Assertions.assertThat(columnHorizontalLine).isEqualTo("+-----");
    }
    @Test
    public void should_be_able_to_create_a_row_line_of_2_columns() {
        LayoutManager layoutManager = new LayoutManager();
        StringBuilder builder=new StringBuilder();
        layoutManager.createColumnHorizontalLine(5);
        String columnHorizontalLine = layoutManager.insertRowLine(builder,2);
        Assertions.assertThat(columnHorizontalLine).isEqualTo("+-----+-----+\n");

    }
    @Test
    public void should_be_able_to_insert_a_cell() {
        LayoutManager layoutManager = new LayoutManager();
        StringBuilder builder=new StringBuilder();
        Cell cell=new Cell("     ");
        String cellData = layoutManager.insertCell(builder, cell);
        Assertions.assertThat(cellData).isEqualTo("|     ");
    }
    @Test
    public void should_be_able_to_close_a_cell() {
        LayoutManager layoutManager = new LayoutManager();
        StringBuilder builder=new StringBuilder();
        String cellData = layoutManager.endCell(builder);
        Assertions.assertThat(cellData).isEqualTo("|\n");
    }


}