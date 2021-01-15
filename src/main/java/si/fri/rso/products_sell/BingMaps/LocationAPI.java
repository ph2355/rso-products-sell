package si.fri.rso.products_sell.BingMaps;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import si.fri.rso.products_sell.filters.UniqueIDClientRequestFilter;

import javax.enterprise.context.Dependent;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/Locations")
@RegisterRestClient
@Dependent
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public interface LocationAPI {

    @GET
    Location getLonAndLat(@QueryParam("query") String query,
                          @QueryParam("maxResults") Integer maxResults,
                          @QueryParam("key") String key);
}
