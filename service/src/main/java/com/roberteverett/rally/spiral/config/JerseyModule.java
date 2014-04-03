package com.roberteverett.rally.spiral.config;

import java.util.HashMap;
import java.util.Map;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class JerseyModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {
        Map<String, String> initParams = new HashMap<String, String>();
        initParams.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
        initParams.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.roberteverett.rally.spiral.resource");

        // route everything through Guice-enabled Jersey
        serve("/*").with(GuiceContainer.class, initParams);
    }

}
