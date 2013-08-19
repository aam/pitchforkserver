package com.aprelev.pitchfork.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alexander on 8/18/13
 */
public class Server {

  public static final String BASE_URI = "http://localhost:8887/pitchfork/";

  public static void main(String[] args) {
    final HttpServer server = startServer();
    System.out.println("App started...");
    try {
      System.in.read();
    } catch (IOException e) {
      Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
    }

    server.stop();
  }

  public static HttpServer startServer() {
    return GrizzlyHttpServerFactory.createHttpServer(
        URI.create(BASE_URI),
        createServer());
  }

  public static ResourceConfig createServer() {
    return new ResourceConfig().
        register(new JettisonFeature()).
        packages("com.aprelev.pitchfork.server");
  }
}
