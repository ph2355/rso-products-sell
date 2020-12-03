package si.fri.rso.products_sell;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("/demo")
public class DemoResource {

    @GET
    public Response getDemo() {
        List<String> clani = new ArrayList<>();
        String opisProjekta = "Aplikacija za prodajo, na podoben način, kot Amazon ali eBay";
        List<String> mikrostoritve = new ArrayList();
        List<String> github = new ArrayList();
        List<String> travis = new ArrayList();
        List<String> dockerhub = new ArrayList();

        clani.add("ph2355");
        mikrostoritve.add("http://20.62.247.7:8080/v1/products");
        github.add("https://github.com/ph2355/rso-products-sell");
        dockerhub.add("https://hub.docker.com/repository/docker/ph2355/rso-products-sell");

        Demo d = new Demo();
        d.setClani(clani);
        d.setOpisProjekta(opisProjekta);
        d.setMikrostoritve(mikrostoritve);
        d.setGithub(github);
        d.setTravis(travis);
        d.setDockerhub(dockerhub);
        return Response.ok(d).build();
    }
}
