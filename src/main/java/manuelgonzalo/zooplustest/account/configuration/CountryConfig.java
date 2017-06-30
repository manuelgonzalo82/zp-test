package manuelgonzalo.zooplustest.account.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.util.LinkedHashMap;

/**
 * Created by manuel on 29/6/17.
 */
@ManagedResource(objectName = "ZooplusTest:name=CountryConfig")
@Configuration
public class CountryConfig {
    private LinkedHashMap<String, String> map;

    @Bean
    public LinkedHashMap<String, String> countryMap() {
        map = new LinkedHashMap<String, String>();
        map.put("AD", "Andorra");
        map.put("AT", "Austria");
        map.put("BE", "Belgium");
        map.put("DK", "Denmark");
        map.put("FI", "Finland");
        map.put("FR", "France");
        map.put("DE", "Germany");
        map.put("GR", "Greece");
        map.put("IT", "Italy");
        map.put("IE", "Ireland");
        map.put("IS", "Iceland");
        map.put("PO", "Poland");
        map.put("PT", "Portugal");
        map.put("RO", "Romania");
        map.put("RU", "Russian Federation");
        map.put("ES", "Spain");
        map.put("SE", "Sweden");
        map.put("TR", "Turkey");
        map.put("GB", "United Kingdom");

        return map;
    }

    @ManagedAttribute
    public LinkedHashMap<String, String> getCountryMap() {
        return map;
    }

    @ManagedOperation
    public void addCountryMap(String twoLetters, String name) {
        map.put(twoLetters, name);
    }
}
