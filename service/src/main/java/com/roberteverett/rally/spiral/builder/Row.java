package com.roberteverett.rally.spiral.builder;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Row<T> {

    private final List<Column<T>> columns;

    public Row(final List<Column<T>> columns) {
        this.columns = Collections.unmodifiableList(columns);
    }

    public List<Column<T>> getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return toString(1);
    }

    public String toString(final int columnSize) {
        final StringBuilder stringBuilder = new StringBuilder();

        final Iterator<Column<T>> columnIterator = columns.iterator();
        while (columnIterator.hasNext()) {
            final Column<T> column = columnIterator.next();
            final String value = asString(column.getValue());

            stringBuilder.append(padSpacesLeft(columnSize, value));

            if (columnIterator.hasNext()) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }

    private String asString(final T value) {
        if (value == null) {
            return "";
        }

        return value.toString();
    }

    private String padSpacesLeft(final int size, final String value) {
        if (size <= 0) {
            return value;
        }

        return String.format("%1$" + size + "s", value);
    }

}
