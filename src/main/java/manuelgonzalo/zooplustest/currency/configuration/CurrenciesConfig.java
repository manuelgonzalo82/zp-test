package manuelgonzalo.zooplustest.currency.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manuel on 29/6/17.
 */
@ManagedResource
@Configuration
public class CurrenciesConfig {
    private List<String> allCurrencies;

    @Bean
    public List<String> currencyList() {
        allCurrencies = new ArrayList<String>();
        allCurrencies.add("AUD");
        allCurrencies.add("CAD");
        allCurrencies.add("CHF");
        allCurrencies.add("EUR");
        allCurrencies.add("GBP");
        allCurrencies.add("HUF");
        allCurrencies.add("JPY");
        allCurrencies.add("NZD");
        allCurrencies.add("USD");
        allCurrencies.add("ZAR");
        return allCurrencies;
    }

    @ManagedAttribute
    public List<String> getAllCurrencies() {
        return allCurrencies;
    }

}
