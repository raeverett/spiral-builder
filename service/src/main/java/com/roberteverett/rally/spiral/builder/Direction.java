package com.roberteverett.rally.spiral.builder;

public enum Direction {
    RIGHT(0, 1), DOWN(1, 0), LEFT(0, -1), UP(-1, 0);

    private final int rowChange;
    private final int columnChange;

    private Direction(final int rowChange, final int columnChange) {
        this.rowChange = rowChange;
        this.columnChange = columnChange;
    }

    public int rowChange() {
        return rowChange;
    }

    public int columnChange() {
        return columnChange;
    }

}
