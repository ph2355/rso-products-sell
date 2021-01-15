package si.fri.rso.products_sell;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class HealthCheckBreakDemo implements HealthCheck {

    @Inject
    HealthBreak healthBreak;

    @Override
    public HealthCheckResponse call() {
        if(healthBreak.getHealthy())
            return HealthCheckResponse.named("Demo").up().build();
        else
            return HealthCheckResponse.named("Demo").down().build();
    }
}
