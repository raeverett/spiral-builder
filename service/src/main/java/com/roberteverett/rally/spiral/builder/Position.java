package com.roberteverett.rally.spiral.builder;

import com.google.inject.Inject;

public class Position {

    private final Rotation rotation;
    private final Direction startDirection;

    private int row;
    private int column;
    private int sizeOfCurrentSide;
    private int sidesFilled;
    private int movesInCurrentDirection;
    private Direction currentDirection;

    @Inject
    public Position(final Direction startDirection, final Rotation rotation) {
        this.rotation = rotation;
        this.startDirection = startDirection;
        this.currentDirection = startDirection;

        this.row = 0;
        this.column = 0;
        this.sizeOfCurrentSide = 1;
        this.sidesFilled = 0;
        this.movesInCurrentDirection = 0;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public void gotoMiddle(final int size) {
        final int dimensions = (int) Math.ceil(Math.sqrt(size));
        final int middle = (int) Math.ceil(dimensions / 2d) - 1;

        // for a matrix with an odd dimensions we can determine the middle spot on
        row = middle;
        column = middle;

        // for even dimensions we need to figure out the "best" of four possible middles based on how we spin
        if (isEven(dimensions)) {
            Direction nextDirection = rotation.nextDirection(startDirection);

            // TODO this is ugly but it works
            row += nextDirection.rowChange() + startDirection.rowChange() == -1 ? 1 : 0;
            column += nextDirection.columnChange() + startDirection.columnChange() == -1 ? 1 : 0;
        }
    }

    public void move() {
        checkForDirectionChange();
        moveInCurrentDirection();
    }

    private void checkForDirectionChange() {
        if (changeDirection()) {
            currentDirection = rotation.nextDirection(currentDirection);

            movesInCurrentDirection = 0;
            sidesFilled++;
        }
    }

    private boolean changeDirection() {
        // change direction after we fill in a side of the spiral matrix
        return movesInCurrentDirection == sizeOfCurrentSide;
    }

    private void moveInCurrentDirection() {
        row += currentDirection.rowChange();
        column += currentDirection.columnChange();

        movesInCurrentDirection++;
        if (increaseDimensions()) {
            sizeOfCurrentSide++;
            sidesFilled = 0;
        }
    }

    private boolean increaseDimensions() {
        // the dimensions of the spiral matrix increase every time we fill in two sides
        return sidesFilled == 2;
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }

}
