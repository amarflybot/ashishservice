package com.home;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String[] args) throws Exception
    {
        try
        {
            new App().run();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }

    public void run() throws Exception {
        Server server = new Server(8080);

        URL webRootLocation = this.getClass().getResource("/index.html");
        if (webRootLocation == null)
        {
            throw new IllegalStateException("Unable to determine webroot URL location");
        }

        URI webRootUri = URI.create(webRootLocation.toURI().toASCIIString().replaceFirst("/index.html$","/"));
        System.err.printf("Web Root URI: %s%n",webRootUri);



        ServletContextHandler ctx = new ServletContextHandler();
        ctx.setContextPath("/");

        DefaultServlet defaultServlet = new DefaultServlet();
        ServletHolder holderPwd = new ServletHolder("default", defaultServlet);
        Resource resource = Resource.newResource(webRootUri);
        System.out.println("Resources: "+ resource.getName());
        holderPwd.setInitParameter("resourceBase", resource.getName());
        holderPwd.setInitParameter("cacheControl", "no-cache");

        ctx.addServlet(holderPwd, "/*");
        //ctx.addServlet(InfoServiceSocketServlet.class, "/info");

        server.setHandler(ctx);
        server.start();
        server.join();
    }
}
