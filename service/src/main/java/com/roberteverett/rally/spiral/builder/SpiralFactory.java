package com.roberteverett.rally.spiral.builder;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class SpiralFactory {

    private final Injector injector;

    @Inject
    public SpiralFactory(final Injector injector) {
        this.injector = injector;
    }

    public ForwardSpiral forwardSpiral() {
        return injector.getInstance(ForwardSpiral.class);
    }

    public ReverseSpiral reverseSpiral() {
        return injector.getInstance(ReverseSpiral.class);
    }

}
