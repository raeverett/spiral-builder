package com.roberteverett.rally.spiral.builder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.google.inject.Inject;

public class SpiralBuilder {

    private final TableBuilder<String> tableBuilder;
    private final int end;
    private final Position position;
    private final Direction startDirection;
    private final Rotation rotation;
    private final List<Integer> values;

    @Inject
    public SpiralBuilder(final TableBuilder<String> tableBuilder, @Named("end") final int end, final Position position,
            final Direction startDirection, final Rotation rotation) {
        this.tableBuilder = tableBuilder;
        this.end = end;
        this.position = position;
        this.startDirection = startDirection;
        this.rotation = rotation;
        this.values = new ArrayList<Integer>();
    }

    public SpiralBuilder add(final int value) {
        values.add(value);
        return this;
    }

    public Spiral build() {
        position.gotoMiddle(values.size());

        for (Integer value : values) {
            tableBuilder.add(position.row(), position.column(), value.toString());
            position.move();
        }

        return new Spiral(end, startDirection, rotation, tableBuilder.removeEmptyRows().build());
    }

}
