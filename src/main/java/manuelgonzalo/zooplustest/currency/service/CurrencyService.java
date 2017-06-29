package manuelgonzalo.zooplustest.currency.service;

/**
 * Created by manuel on 26/6/17.
 */
public interface CurrencyService {
    //public Float getChange();

    public Float getChange(String sourceCurrency, String wantedCurrency);
}
