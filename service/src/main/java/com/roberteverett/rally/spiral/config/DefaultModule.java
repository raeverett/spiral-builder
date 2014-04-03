package com.roberteverett.rally.spiral.config;

import javax.inject.Named;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.servlet.RequestScoped;
import com.roberteverett.rally.spiral.builder.Direction;
import com.roberteverett.rally.spiral.builder.Rotation;
import com.roberteverett.rally.spiral.resource.QueryParameters;

public class DefaultModule extends AbstractModule {

    @Override
    protected void configure() {
        // nothing to see here
        // just-in-time bindings work for most of our injections
    }

    @Provides
    @RequestScoped
    protected Direction provideDirection(final QueryParameters parameters) {
        return parameters.direction();
    }

    @Provides
    @RequestScoped
    protected Rotation provideRotation(final QueryParameters parameters) {
        return parameters.rotation();
    }

    @Provides
    @Named("end")
    @RequestScoped
    protected int provideEnd(final QueryParameters parameters) {
        return parameters.end();
    }

}
