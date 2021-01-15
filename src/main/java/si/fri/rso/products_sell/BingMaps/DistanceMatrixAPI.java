package si.fri.rso.products_sell.BingMaps;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.Dependent;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@Path("/Routes/DistanceMatrix")
@RegisterRestClient
@Dependent
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public interface DistanceMatrixAPI {

    @GET
    DistanceMatrix getDistance(@QueryParam("origins") String origins,
                                                @QueryParam("destinations") String destinations,
                                                @QueryParam("key") String key,
                                                @QueryParam("travelMode") String travelMode);

}
