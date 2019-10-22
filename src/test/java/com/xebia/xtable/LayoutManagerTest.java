package com.xebia.xtable;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LayoutManagerTest {
    @Test
    public void should_be_able_to_create_a_horizontal_line_of_width_5() {
        LayoutManager layoutManager = new LayoutManager();
        String columnHorizontalLine = layoutManager.createHorizontalCellLine(5);
        assertThat(columnHorizontalLine).isEqualTo("+-----");
    }
    @Test
    public void should_be_able_to_create_a_row_line_of_2_columns() {
        LayoutManager layoutManager = new LayoutManager();
        StringBuilder builder=new StringBuilder();
        layoutManager.createHorizontalCellLine(5);
        String columnHorizontalLine = layoutManager.insertRowLine(builder,2);
        assertThat(columnHorizontalLine).isEqualTo("+-----+-----+\n");

    }
    @Test
    public void should_be_able_to_insert_a_cell() {
        LayoutManager layoutManager = new LayoutManager();
        StringBuilder builder=new StringBuilder();
        Cell cell=new Cell("     ");
        String cellData = layoutManager.insertCell(builder, cell);
        assertThat(cellData).isEqualTo("|          ");
    }
    @Test
    public void should_be_able_to_close_a_cell() {
        LayoutManager layoutManager = new LayoutManager();
        StringBuilder builder=new StringBuilder();
        String cellData = layoutManager.closeCell(builder);
        assertThat(cellData).isEqualTo("|\n");
    }

    @Test
    public void should_be_able_to_pad_right_of_given_length() {
        LayoutManager layoutManager = new LayoutManager();
        StringBuilder builder = new StringBuilder();
        builder.append( "text" );
        String s = layoutManager.addPaddingRight( builder, 5 );
        assertThat( s).isEqualTo( "text     " );
    }


}