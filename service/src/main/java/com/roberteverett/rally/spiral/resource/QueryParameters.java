package com.roberteverett.rally.spiral.resource;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestParameters;
import com.google.inject.servlet.RequestScoped;
import com.roberteverett.rally.spiral.builder.Direction;
import com.roberteverett.rally.spiral.builder.Rotation;

/**
 * ASSUMPTION We don't need robust exception handling for this demo, so we'll swallow exceptions and use reasonable
 * defaults.
 */
@RequestScoped
public class QueryParameters {

    public static final Direction DEFAULT_DIRECTION = Direction.RIGHT;
    public static final Rotation DEFAULT_ROTATION = Rotation.CLOCKWISE;
    public static final int DEFAULT_END = 0;

    private final Map<String, String[]> parameters;

    @Inject
    public QueryParameters(@RequestParameters final Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    public Direction direction() {
        try {
            return Direction.valueOf(parameter("direction"));
        } catch (IllegalArgumentException ignored) {
            return DEFAULT_DIRECTION;
        }
    }

    public Rotation rotation() {
        try {
            return Rotation.valueOf(parameter("rotation"));
        } catch (IllegalArgumentException ignored) {
            return DEFAULT_ROTATION;
        }
    }

    public int end() {
        try {
            int validEnd = Integer.parseInt(parameter("end"));
            if (validEnd < 0) {
                return DEFAULT_END;
            }
            return validEnd;
        } catch (NumberFormatException ignored) {
            return DEFAULT_END;
        }
    }

    private String parameter(final String key) {
        if (parameters == null || parameters.get(key) == null) {
            return "";
        }

        return parameters.get(key)[0].toUpperCase();
    }

}
