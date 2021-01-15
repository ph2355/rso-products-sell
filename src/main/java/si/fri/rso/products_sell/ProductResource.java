package si.fri.rso.products_sell;

import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.fri.rso.products_sell.BingMaps.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Log(LogParams.METRICS)
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("products")
public class ProductResource {

    @Inject
    HealthBreak healthBreak;

    @Inject
    private ProductService productBean;

    @Inject
    @RestClient
    Cart cart;

    @GET
    public Response getProducts(@QueryParam("ownerId") Integer ownerId) {
        List<Product> products;
        if(ownerId == null) products = productBean.getProducts();
        else products = productBean.getProductsByOwnerId(ownerId);
        return Response.ok(products).build();
    }

    @GET
    @Path("{productId}")
    public Response getProduct(@PathParam("productId") Integer productId) {
        Product product = productBean.getProduct(productId);
        return product != null
                ? Response.ok(product).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Path("{productId}/image")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getProductImage(@PathParam("productId") Integer productId) {

        ProductImage pi = productBean.getProductImage(productId);

        return pi != null
                ? Response.ok(pi.getImage()).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }


    @POST
    @Path("{productId}/image")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response addProductImage(@PathParam("productId") Integer productId,
                                    InputStream in) {
        ProductImage pi = new ProductImage();
        pi.setProductId(productId);
        try {
            pi.setImage(in.readAllBytes());
            productBean.saveProductImage(pi);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .build();
        }

        return Response.noContent().build();
    }


    @POST
    public Response addNewProduct(Product product) {
        productBean.saveProduct(product);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{productId}")
    public Response deleteProduct(@PathParam("productId") Integer productId) {
        productBean.deleteProduct(productId);
        return Response.noContent().build();
    }

    @POST
    @Path("{productId}/toCart")
    public Response addProductToCart(@PathParam("productId") Integer productId, Integer ownerId) {
        cart.addToCart(ownerId, productId);
        return Response.noContent().build();

    }

    @GET
    @Path("{productId}/distance")
    public Response getDistanceToProduct(@PathParam("productId") Integer productId, @QueryParam("destination") String destination) {
        return Response.ok(productBean.getDistanceToProduct(productId, destination)).build();
    }

}
