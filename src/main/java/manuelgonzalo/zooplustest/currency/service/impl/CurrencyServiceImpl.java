package manuelgonzalo.zooplustest.currency.service.impl;

import manuelgonzalo.zooplustest.currency.CurrencyLayerObject;
import manuelgonzalo.zooplustest.currency.service.CurrencyService;
import manuelgonzalo.zooplustest.utils.InMemoryCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manuel on 26/6/17.
 */
@Service("currencyServer")
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private InMemoryCache<String, Float> externalRequestsCache;

    @Override
    public Float getChange(String sourceCurrency, String wantedCurrency) {
        String key = sourceCurrency + wantedCurrency;
        Float value = getChangeFromCache(key);

        if (value == null) {
            String dollarSourceKey = "USD" + sourceCurrency;
            String dollarSourceReverseKey = sourceCurrency + "USD";
            Float valueSource = getChangeFromCache(dollarSourceKey);

            String dollarWantedKey = "USD" + wantedCurrency;
            String dollarWantedReverseKey = wantedCurrency + "USD";
            Float valueWanted = getChangeFromCache(dollarWantedKey);

            if (valueSource == null || valueWanted == null) {
                Map<String, Float> resFromApi = getChangeFromApi(sourceCurrency, wantedCurrency);
                if (resFromApi != null) {
                    valueSource = resFromApi.get(dollarSourceKey);
                    setChangeInCache(dollarSourceKey, valueSource);
                    setChangeInCache(dollarSourceReverseKey, 1 / valueSource);

                    valueWanted = resFromApi.get(dollarWantedKey);
                    setChangeInCache(dollarWantedKey, valueWanted);
                    setChangeInCache(dollarWantedReverseKey, 1 / valueWanted);

                    value = valueWanted / valueSource;
                    setChangeInCache(key, value);
                }
            }
        }

        return value;
    }

    public Float getChangeFromCache(String key) {
        Float value = externalRequestsCache.get(key);
        System.out.println("getChangeFromCache: " + value);
        return value;
    }

    public void setChangeInCache(String key, Float value) {
        externalRequestsCache.put(key, value);
        System.out.println("setChangeFromCache: " + key + " -> " + value);
    }

    private Map<String, Float> getChangeFromApi(String sourceCurrency, String wantedCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String BASE_URL = "http://apilayer.net";
        Map<String, Float> result = new HashMap<String, Float>();
        URI targetUrl= UriComponentsBuilder.fromUriString(BASE_URL)
                .path("/api/live")
                .queryParam("access_key", "59614587616356c067d51ef17e649073")
                .queryParam("format", "1")
                .queryParam("currencies", sourceCurrency + "," + wantedCurrency)
                .build()
                .encode()
                .toUri();
        CurrencyLayerObject clo = restTemplate.getForObject(targetUrl, CurrencyLayerObject.class);
        String rateSourceStr = clo.getQuotes().get("USD" + sourceCurrency);
        String rateWantedStr = clo.getQuotes().get("USD" + wantedCurrency);

        try {
            result.put("USD" + sourceCurrency, Float.parseFloat(rateSourceStr));
            result.put("USD" + wantedCurrency, Float.parseFloat(rateWantedStr));
            return result;
        } catch (NumberFormatException nfe) {
            System.out.println("Error " + nfe.getMessage());
            return null;
        }
    }
}
