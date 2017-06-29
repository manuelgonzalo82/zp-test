package manuelgonzalo.zooplustest.currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by manuel on 25/6/17.
 */
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        String BASE_URL = "http://apilayer.net";
        URI targetUrl= UriComponentsBuilder.fromUriString(BASE_URL)
                .path("/api/live")
                .queryParam("access_key", "59614587616356c067d51ef17e649073")
                .queryParam("format", "1")
                .queryParam("currencies", "USD,AUD,CAD,PLN,EUR")
                .build()
                .encode()
                .toUri();
        CurrencyLayerObject clo = restTemplate.getForObject(targetUrl, CurrencyLayerObject.class);
        log.info(clo.toString());
    }
}
