package com.aprelev.pitchfork.server;

import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;

import javax.inject.Singleton;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

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
          (new Formatter()).format("%s", r.name).toString(),
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

      Client c = ClientBuilder.newClient();
      c.register(new HttpBasicAuthFilter("userid", "password"));
      c.register(JsonProcessingFeature.class);

      WebTarget target = c.target("http://dell.aprelev.com:8080/rest/api/2/");
      String versionsMsg = target.path("project").path("PIT").path("versions").request().get(String.class);

      JsonObject jiras = target.path("search").queryParam("jql", "fixVersion=R1").request().get(JsonObject.class);
      JsonArray issues = jiras.getJsonArray("issues");

      Branch branch02 = new Branch("Branch-02", "Kate");
      Release r = new Release("R01", "Go for best implementation", "This is my era plan",
          new DepJIRA("DepJIRA-01", "releaseNotes for DepJIRA-01.txt",
              new DevJIRA("DevJIRA-10",
                  new Branch("Branch-01", "Vasya Pupkin"),
                  branch02,
                  new Branch("Branch-03", "Ioann")),
              new DevJIRA("DevJIRA-11",
                  new Branch("Branch-05", "Vasya Pupkin"),
                  branch02,
                  new Branch("Branch-07", "Ioann"))),
          new DepJIRA("DepJIRA-02", "Gorlym",
              new DevJIRA("DevJIRA-20",
                  new Branch("Branch-21", "Seva"),
                  branch02,
                  new Branch("Branch-23", "Stetoskop")),
              new DevJIRA("DevJIRA-21",
                  new Branch("Branch-25", "Seva"),
                  branch02,
                  new Branch("Branch-27", "Stetoskop")))
          );
      releases.put(r.name, r);
      releases.put("R05", new Release("R05", "Implementation is very important", "ERA this"));
      releases.put("R10", new Release("R10", "Why implementation?", "This ERA rules"));
    }
    return releases;
  }
}
