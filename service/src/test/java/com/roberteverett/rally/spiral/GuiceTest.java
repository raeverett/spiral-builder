package com.roberteverett.rally.spiral;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.util.Modules;
import com.roberteverett.rally.spiral.config.GuiceConfig;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

/**
 * This wires Guice into our test harness and allows us to override injection with testable mocks and stubs.
 */
public class GuiceTest extends JerseyTest {

    @Override
    protected WebAppDescriptor configure() {
        WebAppDescriptor.Builder builder = new WebAppDescriptor.Builder();
        return builder.filterClass(GuiceFilter.class).contextListenerClass(TestConfig.class).build();
    }

    public static class TestConfig extends GuiceConfig {

        @Override
        protected List<Module> modules() {
            List<Module> modules = new ArrayList<Module>();
            modules.add(Modules.override(super.modules()).with(new TestModule()));
            return modules;
        }
    }

    public static class TestModule extends AbstractModule {

        @Override
        protected void configure() {
        }
    }

}
