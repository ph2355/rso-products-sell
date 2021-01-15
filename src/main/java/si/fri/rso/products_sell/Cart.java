package si.fri.rso.products_sell;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import si.fri.rso.products_sell.filters.UniqueIDClientRequestFilter;

import javax.enterprise.context.Dependent;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RegisterProvider(UniqueIDClientRequestFilter.class)
@Path("/cart")
@RegisterRestClient
@Dependent
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface Cart {

    @POST
    @Path("{ownerId}")
    void addToCart(@PathParam("ownerId") Integer ownerId, Integer productId);

    @GET
    @Path("faultToleranceDemo")
    String faultToleranceDemo();
}
