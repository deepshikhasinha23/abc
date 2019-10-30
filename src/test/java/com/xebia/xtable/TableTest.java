package com.xebia.xtable;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class TableTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_be_able_to_initialize_a_table_with_M_rows_N_columns() {
        Table table = new Table(new Configuration.Builder().withRow(5).withColumn(2).build() );
        String shape = table.shape();
        assertThat(shape).isEqualTo("(5*2)");

    }

    @Test
    public void should_be_able_to_create_an_empty_table_of_M_rows_N_columns() {
        Table table = Table.getEmptyTable( 1, 2 );
        String tableStructure =
                        "+----------+----------+\n" +
                        "|          |          |\n" +
                        "+----------+----------+\n";
        String createdTable = table.create();
        assertThat( createdTable ).isEqualTo( tableStructure );
    }

    @Test
    public void should_be_able_to_throw_exception_when_any_of_M_or_N_is_zero_or_less_than_zero() {
        expectedException.expect( IllegalArgumentException.class );
        expectedException.expectMessage( "Invalid number of rows or columns" );
        new Table( new Configuration.Builder().withRow( 0 ).withColumn( 2 ).build() );
    }

    @Test
    public void should_be_able_to_create_a_table_with_headers() {
        Table table = new Table( new Configuration.Builder().withRow( 2 ).withColumn( 2 ).build() );
        table.addHeader( "header1", "header2" );
        String s = table.create();
        String tableWithHeaders =
                "+----------+----------+\n" +
                "|HEADER1   |HEADER2   |\n" +
                "+----------+----------+\n" +
                "|          |          |\n" +
                "+----------+----------+\n";
        assertThat( s ).isEqualTo( tableWithHeaders );
    }

    @Test
    public void should_be_able_to_throw_exception_when_number_of_headers_is_not_equal_to_number_of_columns() {
        Table table = new Table( new Configuration.Builder().withRow( 5 ).withColumn( 2 ).build() );
        expectedException.expect( IllegalArgumentException.class );
        expectedException.expectMessage( "Number of headers should be equal to number of columns." );
        table.addHeader( "header1", "header2", "headers3" );
    }

    @Test
    public void should_be_able_to_add_rows_with_data() {
        Table table = new Table( new Configuration.Builder().withRow( 3 ).withColumn( 2 ).build() );
        table.addHeader( "header1", "header2" );
        table.addDataInRow( "data1", "data2" )
                .addDataInRow( "data1", "data2" );
        String s = table.create();
        String tableWithData =
                        "+----------+----------+\n" +
                        "|HEADER1   |HEADER2   |\n" +
                        "+----------+----------+\n" +
                        "|data1     |data2     |\n" +
                        "+----------+----------+\n" +
                        "|data1     |data2     |\n" +
                        "+----------+----------+\n";
        assertThat( s ).isEqualTo( tableWithData );
    }

    @Test
    public void should_be_able_to_throw_exception_when_add_data_in_rows_and_table_is_full() {
        Table table = new Table( new Configuration.Builder().withRow( 2 ).withColumn( 2 ).build() );
        table.addHeader( "header1", "header2" );
        table.addDataInRow( "data1", "data2" );
        expectedException.expect( IllegalStateException.class );
        expectedException.expectMessage( "Table is full" );
        table.addDataInRow( "data1", "data2" );
    }

    @Test
    public void should_be_able_to_create_a_vertical_table() {
        Table table = new Table( new Configuration.Builder().withRow( 3 ).withColumn( 2 ).build() );
        table.addHeader( "header1", "header2" );
        table.addDataInRow( "data1", "data2" )
                .addDataInRow( "data1", "data2" );
        String s = table.create( LayoutOptions.VERTICAL );
        String tableWithData =
                        "+----------+----------+----------+\n" +
                        "|HEADER1   |data1     |data1     |\n" +
                        "+----------+----------+----------+\n" +
                        "|HEADER2   |data2     |data2     |\n" +
                        "+----------+----------+----------+\n";
        assertThat( s ).isEqualTo( tableWithData );
    }

    @Test
    public void should_be_able_to_create_an_empty_table_of_M_rows_N_columns_with_specific_width() {
        Table table = new Table( new Configuration.Builder().withRow( 1 ).withColumn( 2 ).withColumnWidths( 20 ).build() );
        String tableStructure =
                        "+--------------------+--------------------+\n" +
                        "|                    |                    |\n" +
                        "+--------------------+--------------------+\n";
        String createdTable = table.create();
        assertThat( tableStructure ).isEqualTo( createdTable );
    }

    @Test
    public void should_be_able_to_create_a_table_of_M_rows_N_columns_with_specific_width_containing_headers_and_data() {
        Table table = new Table( new Configuration.Builder().withRow( 2 ).withColumn( 2 ).withColumnWidths( 20 ).build() );
        table.addHeader( "header1", "header2" );
        table.addDataInRow( "data1", "data2" );
        String tableStructure =
                        "+--------------------+--------------------+\n" +
                        "|HEADER1             |HEADER2             |\n" +
                        "+--------------------+--------------------+\n" +
                        "|data1               |data2               |\n" +
                        "+--------------------+--------------------+\n";
        String createdTable = table.create();
        assertThat( tableStructure ).isEqualTo( createdTable );
    }

    @Test
    public void should_be_able_to_create_a_vertical_table_with_specific_width() {
        Table table = new Table( new Configuration.Builder().withRow( 3 ).withColumn( 2 ).withColumnWidths( 15 ).build() );
        table.addHeader( "header1", "header2" );
        table.addDataInRow( "data1", "data2" )
                .addDataInRow( "data1", "data2" );
        String s = table.create( LayoutOptions.VERTICAL );
        String tableWithData =
                        "+---------------+---------------+---------------+\n" +
                        "|HEADER1        |data1          |data1          |\n" +
                        "+---------------+---------------+---------------+\n" +
                        "|HEADER2        |data2          |data2          |\n" +
                        "+---------------+---------------+---------------+\n";
        assertThat( s ).isEqualTo( tableWithData );
    }

    @Test
    public void should_be_able_to_create_a_horizontal_table_with_different_widths() {
        Table table = new Table( new Configuration.Builder().withRow( 3 ).withColumn( 2 ).withColumnWidths( 15, 12 ).build() );
        table.addHeader( "header1", "header2" );
        table.addDataInRow( "data1", "data2" )
                .addDataInRow( "data1", "data2" );
        String s = table.create();
        String tableWithData =
                        "+---------------+------------+\n" +
                        "|HEADER1        |HEADER2     |\n" +
                        "+---------------+------------+\n" +
                        "|data1          |data2       |\n" +
                        "+---------------+------------+\n" +
                        "|data1          |data2       |\n" +
                        "+---------------+------------+\n";
        assertThat( s ).isEqualTo( tableWithData );
    }

    @Test
    public void should_be_able_to_create_a_vertical_table_with_max_if_widths_are_different() {
        Table table = new Table( new Configuration.Builder().withRow( 3 ).withColumn( 2 ).withColumnWidths( 15, 12 ).build() );
        table.addHeader( "header1", "header2" );
        table.addDataInRow( "data1", "data2" )
                .addDataInRow( "data1", "data2" );
        String s = table.create( LayoutOptions.VERTICAL );
        String tableWithData =
                        "+---------------+---------------+---------------+\n" +
                        "|HEADER1        |data1          |data1          |\n" +
                        "+---------------+---------------+---------------+\n" +
                        "|HEADER2        |data2          |data2          |\n" +
                        "+---------------+---------------+---------------+\n";
        assertThat( s ).isEqualTo( tableWithData );
    }

    @Test
    public void should_be_able_to_throw_exception_when_given_number_of_widths_are_not_equal_to_0_1_or_to_number_of_column() {
        expectedException.expect( IllegalArgumentException.class );
        expectedException.expectMessage( "Number of column width should be 1 or equal to number of columns" );
        new Table( new Configuration.Builder().withRow( 3 ).withColumn( 5 ).withColumnWidths( 15, 12, 13 ).build() );

    }

    @Test
    public void should_be_able_to_throw_exception_when_number_of_input_data_is_not_equal_to_number_of_columns() {
        Table table = new Table( new Configuration.Builder().withRow( 3 ).withColumn( 2 ).withColumnWidths( 15, 12 ).build() );
        table.addHeader( "header1", "header2" );
        expectedException.expect( IllegalArgumentException.class );
        expectedException.expectMessage( "Number of data in a row should be equal to number of columns" );
        table.addDataInRow( "data1", "data2", "data3" );
    }

}