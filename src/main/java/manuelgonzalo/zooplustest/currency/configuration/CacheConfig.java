package manuelgonzalo.zooplustest.currency.configuration;

import manuelgonzalo.zooplustest.utils.InMemoryCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Created by manuel on 26/6/17.
 */
@ManagedResource
@Configuration
public class CacheConfig {
    private InMemoryCache<String, Float> cache;

    @Bean
    public InMemoryCache<String, Float> externalRequestsCache() {
        cache = new InMemoryCache<String, Float>(1000, 10, 10);
        return cache;
    }

    @ManagedAttribute
    public InMemoryCache<String, Float> getCache() {
        return cache;
    }
}
