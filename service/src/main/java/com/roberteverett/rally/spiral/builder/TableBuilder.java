package com.roberteverett.rally.spiral.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TableBuilder<T> {

    private final List<List<T>> table;

    public TableBuilder() {
        this.table = new ArrayList<List<T>>();
    }

    public TableBuilder<T> add(final int row, final int column, final T value) {
        padTable(row, column);
        table.get(row).set(column, value);

        return this;
    }

    public TableBuilder<T> removeEmptyRows() {
        Iterator<List<T>> rows = table.iterator();

        while (rows.hasNext()) {
            if (rows.next().isEmpty()) {
                rows.remove();
            }
        }

        return this;
    }

    private void padTable(final int row, final int column) {
        while (row >= table.size()) {
            table.add(new ArrayList<T>());
        }

        List<T> values = table.get(row);
        while (column >= values.size()) {
            values.add(null);
        }
    }

    public Table<T> build() {
        final List<Row<T>> rows = new ArrayList<Row<T>>();

        for (List<T> row : table) {
            final List<Column<T>> columns = new ArrayList<Column<T>>();

            for (T value : row) {
                columns.add(new Column<T>(value));
            }

            rows.add(new Row<T>(columns));
        }

        return new Table<T>(rows);
    }

}
