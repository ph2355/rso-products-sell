package si.fri.rso.products_sell.filters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@ApplicationScoped
public class UniqueIDClientRequestFilter implements ClientRequestFilter {

    @Inject
    AdditionalHeaderData additionalHeaderData;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
//        String s = additionalHeaderData.getRequest_id();
//        requestContext.getHeaders().add("Request-ID", s);
    }
}
