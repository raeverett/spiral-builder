package com.roberteverett.rally.spiral.builder;

import java.util.ArrayList;
import java.util.List;

public enum Rotation {
    CLOCKWISE(1), COUNTER_CLOCKWISE(-1);

    private final int ordinalChange;

    private Rotation(final int ordinalChange) {
        this.ordinalChange = ordinalChange;
    }

    public Direction nextDirection(final Direction currentDirection) {
        // when index change is outside of bounds, wrap back around
        int nextIndex = (clockwise.indexOf(currentDirection) + ordinalChange + clockwise.size()) % clockwise.size();
        return clockwise.get(nextIndex);
    };

    private static final List<Direction> clockwise;
    static {
        clockwise = new ArrayList<Direction>();
        clockwise.add(Direction.RIGHT);
        clockwise.add(Direction.DOWN);
        clockwise.add(Direction.LEFT);
        clockwise.add(Direction.UP);
    }

}