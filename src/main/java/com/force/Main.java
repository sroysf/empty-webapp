package com.force;

import java.io.File;
import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.force.servlet.HelloServlet;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        logger.info("Hello world");

        Server server = new Server(8080);

        String webAppDirLocation = Main.class.getClassLoader().getResource("webapp").toURI().toString();

        WebAppContext root = new WebAppContext();
        root.setContextPath("/");
        root.setDescriptor(webAppDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webAppDirLocation);

        // Parent loader priority is a class loader setting that Jetty accepts.
        // By default Jetty will behave like most web containers in that it will
        // allow your application to replace non-server libraries that are part
        // of the
        // container. Setting parent loader priority to true changes this
        // behavior.
        // Read more here:
        // http://wiki.eclipse.org/Jetty/Reference/Jetty_Classloading
        root.setParentLoaderPriority(true);

        server.setHandler(root);

        server.start();
        server.join();
    }
}
