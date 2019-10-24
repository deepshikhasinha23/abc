package com.xebia.xtable;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ElementUtilsTest {
    @Test
    public void should_be_able_to_create_a_horizontal_line_of_width_5() {
        String columnHorizontalLine = ElementUtils.createHorizontalCellLine(5);
        assertThat(columnHorizontalLine).isEqualTo("+-----");
    }

    @Test
    public void should_be_able_to_create_a_row_line_of_2_columns() {
        StringBuilder builder = new StringBuilder();
        String line = ElementUtils.createHorizontalCellLine(5);
        String columnHorizontalLine = ElementUtils.insertRowLine(line, builder, 2);
        assertThat(columnHorizontalLine).isEqualTo("+-----+-----+\n");

    }

    @Test
    public void should_be_able_to_insert_a_cell() {
        StringBuilder builder = new StringBuilder();
        String cellData = ElementUtils.insertCell(builder, Cell.createEmpty());
        assertThat(cellData).isEqualTo("|          ");
    }

    @Test
    public void should_be_able_to_close_a_cell() {
        StringBuilder builder = new StringBuilder();
        String cellData = ElementUtils.closeCell(builder);
        assertThat(cellData).isEqualTo("|\n");
    }

    @Test
    public void should_be_able_to_pad_right_of_given_length() {
        StringBuilder builder = new StringBuilder();
        builder.append("text");
        String s = ElementUtils.addPaddingRight(builder, 5);
        assertThat(s).isEqualTo("text     ");
    }


}