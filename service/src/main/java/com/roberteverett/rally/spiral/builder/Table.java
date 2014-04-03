package com.roberteverett.rally.spiral.builder;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Table<T> {

    private final List<Row<T>> rows;

    public Table(final List<Row<T>> rows) {
        this.rows = Collections.unmodifiableList(rows);
    }

    public List<Row<T>> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        final int padSize = padSize();
        final StringBuilder stringBuilder = new StringBuilder();

        final Iterator<Row<T>> rowIterator = rows.iterator();
        while (rowIterator.hasNext()) {
            final Row<T> row = rowIterator.next();
            stringBuilder.append(row.toString(padSize));

            if (rowIterator.hasNext()) {
                stringBuilder.append(platformIndependentNewLine());
            }
        }

        return stringBuilder.toString();
    }

    private String platformIndependentNewLine() {
        return String.format("%n");
    }

    private int padSize() {
        int maxColumnSize = 0;

        for (Row<T> row : rows) {
            for (Column<T> column : row.getColumns()) {
                final T value = column.getValue();
                if (value == null) {
                    continue;
                }

                maxColumnSize = Math.max(maxColumnSize, value.toString().length());
            }
        }

        return maxColumnSize;
    }
}
