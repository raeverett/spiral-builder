package com.roberteverett.rally.spiral.config;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceConfig extends GuiceServletContextListener {

    @Override
    protected final Injector getInjector() {
        return Guice.createInjector(modules());
    }

    /**
     * Override this method to return Guice modules to use for this context.
     */
    protected List<Module> modules() {
        List<Module> modules = new ArrayList<Module>();
        modules.add(new JerseyModule());
        modules.add(new DefaultModule());
        return modules;
    }

}
