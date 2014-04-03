package com.roberteverett.rally.spiral.builder;

import com.google.inject.Inject;
import com.roberteverett.rally.spiral.resource.QueryParameters;

public class ReverseSpiral {

    private final SpiralBuilder builder;
    private final QueryParameters parameters;

    @Inject
    public ReverseSpiral(final SpiralBuilder builder, final QueryParameters parameters) {
        this.builder = builder;
        this.parameters = parameters;
    }

    public Spiral build() {
        for (int i = parameters.end(); i >= 0; i--) {
            builder.add(Integer.valueOf(i));
        }

        return builder.build();
    }
}
