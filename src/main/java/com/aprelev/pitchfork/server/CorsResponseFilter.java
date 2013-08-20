package com.aprelev.pitchfork.server;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


/**
 * Created by alexander on 8/19/13
 */
@Provider
public class CorsResponseFilter implements ContainerResponseFilter {

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
      throws IOException {

    responseContext.getHeaders().add("X-Powered-By", "Jersey :-)");
    responseContext.getHeaders().add("Access-Control-Allow-Origin", "*, ");
    responseContext.getHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
    responseContext.getHeaders().add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  }
}
