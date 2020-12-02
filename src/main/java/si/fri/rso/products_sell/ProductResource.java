package si.fri.rso.products_sell;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("products")
public class ProductResource {

    @Inject
    private ProductService productBean;

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
}
