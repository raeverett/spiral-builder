package com.roberteverett.rally.spiral.builder;

public class Spiral {

    private final int end;
    private final Direction direction;
    private final Rotation rotation;
    private final Table<String> table;

    public Spiral(final int end, final Direction direction, final Rotation rotation, final Table<String> table) {
        this.end = end;
        this.direction = direction;
        this.rotation = rotation;
        this.table = table;
    }

    public int getEnd() {
        return end;
    }

    public Direction getDirection() {
        return direction;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Table<String> getTable() {
        return table;
    }

    @Override
    public String toString() {
        return table.toString();
    }

}
