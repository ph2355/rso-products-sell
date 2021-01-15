package si.fri.rso.products_sell.BingMaps;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
//@ConfigBundle("bing")
public class BingConfig {

//    @ConfigValue(watch = true)
    private String apikey = "AnOfR1DTomWos2EXmUvhJSbunF40TaljlmB2M850AqIvUNYGOjiHbaQXUBMDgQWr";

    public String getApikey() {
        return apikey;
    }

    public void setApiKey(String apikey) {
        this.apikey = apikey;
    }
}
