package com.xebia.xtable;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ElementUtilsTest {
    @Test
    public void should_be_able_to_create_a_horizontal_line_of_width_5() {
        String columnHorizontalLine = ElementUtils.createHorizontalCellLine( 5 );
        assertThat( columnHorizontalLine ).isEqualTo( "+-----" );
    }

    @Test
    public void should_be_able_to_create_a_cell_line_of_width_10() {
        String line = ElementUtils.createHorizontalCellLine( 10 );
        assertThat( line ).isEqualTo( "+----------" );

    }

    @Test
    public void should_be_able_to_insert_a_cell() {
        StringBuilder builder = new StringBuilder();
        String cellData = ElementUtils.insertCell( builder, Cell.createEmpty( 10 ) );
        assertThat( cellData ).isEqualTo( "|          " );
    }

    @Test
    public void should_be_able_to_close_a_cell() {
        StringBuilder builder = new StringBuilder();
        String cellData = ElementUtils.closeCell( builder );
        assertThat( cellData ).isEqualTo( "|\n" );
    }

    @Test
    public void should_be_able_to_pad_right_of_given_length() {
        StringBuilder builder = new StringBuilder();
        builder.append( "text" );
        String s = ElementUtils.addPaddingRight( builder, 5 );
        assertThat( s ).isEqualTo( "text     " );
    }

    @Test
    public void should_be_able_to_truncate_data_if_data_exceeds_cell_length() {
        Cell cell = new Cell( "My name is Ram", 10,false );
        String s = ElementUtils.truncateData( cell );
        assertThat( s ).isEqualTo( "My name..." );
    }


    @Test
    public void should_be_able_to_insert_a_line_for_horizontal_table() {
        String s = ElementUtils.insertRowLine( new Configuration.Builder().withRow( 2 ).withColumn( 2 ).withColumnWidths( 15,13 ).build(),
                new StringBuilder() ,LayoutOptions.HORIZONTAL);
        assertThat( s ).isEqualTo( "+---------------+-------------+\n" );
    }

}