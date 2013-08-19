package com.aprelev.pitchfork.server;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by alexander on 8/18/13
 */
@Singleton
@Path("/releases")
public class ReleasesResource {
  Map<String, Release> releases;
  static WebResourceList myResources;

  @Context
  UriInfo uriInfo;

  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public WebResourceList getMyResources() {
    if (myResources == null) {
      myResources = new WebResourceList();
      myResources.items = new LinkedList<WebResourceList.Item>();
      myResources.items.add(new WebResourceList.Item(
          "List of releases", uriInfo.getBaseUriBuilder().path(this.getClass()).path("list").build().toString()));
    }
    return myResources;
  }

  @GET @Path("/list")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public WebResourceList getListOfReleases() {
    WebResourceList result = new WebResourceList();
    result.items = new LinkedList<WebResourceList.Item>();
    for (Release r: getReleases().values()) {
      result.items.add(new WebResourceList.Item(
          (new Formatter()).format("%s (by %s)", r.name, r.author).toString(),
          uriInfo.getBaseUriBuilder().path(this.getClass()).path("ids").path(r.name).build().toString()));

    }
    return result;
  }

  @GET @Path("/ids/{releasename}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Release getRelease(@PathParam("releasename") String releaseName) {
    return getReleases().get(releaseName);
  }

  private Map<String, Release> getReleases() {
    if (releases == null) {
      releases = new HashMap<String, Release>();
      releases.put("R01", new Release("R01", "Vasya Pupkin"));
      releases.put("R05", new Release("R05", "Ivan Golozaderischenskiy"));
      releases.put("R10", new Release("R10", "Seva Laptev"));
    }
    return releases;
  }
}
