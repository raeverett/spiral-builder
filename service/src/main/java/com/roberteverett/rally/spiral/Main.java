package com.roberteverett.rally.spiral;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.servlet.GuiceFilter;
import com.roberteverett.rally.spiral.config.GuiceConfig;

public class Main {

    public static void main(final String[] args) throws Exception {
        Server server = new Server(31415); // that's pi!
        ServletContextHandler serverContextHandler = new ServletContextHandler(server, "/");

        bootstrapGuice(serverContextHandler);
        routeRequestsThroughGuice(serverContextHandler);

        server.start();
        server.join();
    }

    private static void routeRequestsThroughGuice(ServletContextHandler serverContextHandler) {
        serverContextHandler.addFilter(GuiceFilter.class, "/*", null);
    }

    private static void bootstrapGuice(ServletContextHandler serverContextHandler) {
        serverContextHandler.addEventListener(new GuiceConfig());
    }

}
