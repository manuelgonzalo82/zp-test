package manuelgonzalo.zooplustest.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * Created by manuel on 25/6/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyLayerObject {
    private String success;
    private long timestamp;
    private String source;
    private Map<String, String> quotes;

    public CurrencyLayerObject() {

    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Map<String, String> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, String> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "CurrencyLayerObject{" +
                "success='" + success + '\'' +
                ", timestamp=" + timestamp +
                ", source='" + source + '\'' +
                '}';
    }
}
