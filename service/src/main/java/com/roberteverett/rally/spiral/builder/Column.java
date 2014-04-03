package com.roberteverett.rally.spiral.builder;

public class Column<T> {

    private final T value;

    public Column(final T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

}
