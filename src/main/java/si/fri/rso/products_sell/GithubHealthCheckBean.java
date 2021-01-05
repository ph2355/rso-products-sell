package si.fri.rso.products_sell;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import java.net.HttpURLConnection;
import java.net.URL;

@Readiness
@ApplicationScoped
public class GithubHealthCheckBean implements HealthCheck {

    private static final String url = "https://github.com/kumuluz/kumuluzee";

    @Override
    public HealthCheckResponse call() {
        try {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");

            if (connection.getResponseCode() == 200) {
                return HealthCheckResponse.named(GithubHealthCheckBean.class.getSimpleName()).up().build();
            }
        } catch (Exception exception) {
        }
        return HealthCheckResponse.named(GithubHealthCheckBean.class.getSimpleName()).down().build();
    }
}