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
        Table table = new Table( 5, 2 );
        String shape = table.shape();
        assertThat( shape ).isEqualTo( "(5*2)" );

    }

    @Test
    public void should_be_able_to_create_an_empty_table_of_M_rows_N_columns() {
        Table table = new Table( 1, 2 );
        String tableStructure =
                        "+----------+----------+\n" +
                        "|          |          |\n" +
                        "+----------+----------+\n";
        String createdTable = table.create();
        assertThat( tableStructure ).isEqualTo( createdTable );
    }

    @Test
    public void should_be_able_to_throw_exception_when_any_of_M_or_N_is_zero_or_less_than_zero() {
        expectedException.expect( IllegalArgumentException.class );
        expectedException.expectMessage( "Invalid number of rows or columns" );
        new Table( 0, 2 );
    }

    @Test
    public void should_be_able_to_alter_the_dimensions_of_table() {
        Table table = new Table( 1, 2 );
        table.addNumberOfRows( 3 );
        table.addNumberOfColumns( 2 );
        String shape = table.shape();
        assertThat( shape ).isEqualTo( "(4*4)" );
    }

    @Test
    public void should_be_able_to_throw_exception_when_reducing_quantity_of_row_greater_than_current_no_of_columns() {
        Table table = new Table( 1, 2 );
        table.addNumberOfRows( 3 );
        expectedException.expect( IllegalArgumentException.class );
        expectedException.expectMessage( "Argument should be lesser than current number of columns" );
        table.reduceNumberOfColumns( 3 );
    }
}