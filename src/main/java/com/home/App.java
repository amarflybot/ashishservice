package com.home;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.net.URL;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {
        try {
            new App().run();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void run() throws Exception {
        Server server = new Server(8080);

        URL webRootLocation = this.getClass().getResource("/static/index.html");
        if (webRootLocation == null) {
            throw new IllegalStateException("Unable to determine webroot URL location");
        }

        URI webRootUri = URI.create(webRootLocation.toURI().toASCIIString().replaceFirst("/index.html$", "/"));
        System.err.printf("Web Root URI: %s%n", webRootUri);


        ServletContextHandler ctx = new ServletContextHandler();
        ctx.setContextPath("/");

        DefaultServlet defaultServlet = new DefaultServlet();
        ServletHolder webApp = new ServletHolder("default", defaultServlet);
        Resource resource = Resource.newResource(webRootUri);
        System.out.println("Resources: " + resource.getName());
        webApp.setInitParameter("resourceBase", resource.getName());
        webApp.setInitParameter("cacheControl", "no-cache");

        ctx.addServlet(webApp, "/*");

        ResourceConfig application = new ResourceConfig()
                .packages("com.home.controller","com.home.binder")
                .register(JacksonFeature.class);

        ServletHolder jerseyServlet = new ServletHolder(new
                org.glassfish.jersey.servlet.ServletContainer(application));
        jerseyServlet.setInitOrder(0);
        ctx.addServlet(jerseyServlet, "/api/*");

        server.setHandler(ctx);
        server.start();
        server.join();
    }
}
