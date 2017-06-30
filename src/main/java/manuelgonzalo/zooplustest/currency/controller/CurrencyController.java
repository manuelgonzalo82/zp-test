package manuelgonzalo.zooplustest.currency.controller;

import manuelgonzalo.zooplustest.account.service.UserService;
import manuelgonzalo.zooplustest.currency.model.HistoricalRecord;
import manuelgonzalo.zooplustest.currency.service.CurrencyService;
import manuelgonzalo.zooplustest.currency.service.HistoricalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by manuel on 26/6/17.
 */
@RestController
@EnableAutoConfiguration
public class CurrencyController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private HistoricalRecordService historicalRecordService;

    @Autowired
    private List<String> currencyList;

    @RequestMapping(value={"/currency/check"}, method = RequestMethod.GET)
    public ModelAndView checkPage() {
        List<HistoricalRecord> historicalRecordList = null;
        try {
            historicalRecordList = getHistoricalRecordList();
        } catch (Exception e) {

        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("historicalRecordList", historicalRecordList);
        modelAndView.addObject("allCurrencies", currencyList);
        modelAndView.setViewName("check");
        return modelAndView;
    }

    @RequestMapping(value={"/currency/check"}, method = RequestMethod.POST)
    public ModelAndView makeCurrencyRequest(@RequestParam("sourceCurrency") String sourceCurrency, @RequestParam("wantedCurrency") String wantedCurrency) {

        /// Retrieve historical records
        List<HistoricalRecord> historicalRecordList = null;

        /// If wrong input parameters
        if (!currencyList.contains(sourceCurrency) || !currencyList.contains(wantedCurrency)) {

            try {
                historicalRecordList = getHistoricalRecordList();
            } catch (Exception e) {

            }
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("historicalRecordList", historicalRecordList);
            modelAndView.addObject("allCurrencies", currencyList);
            modelAndView.addObject("customError", true);
            modelAndView.setViewName("check");
            return modelAndView;
        }

        Float value = null;
        try {
            value = currencyService.getChange(sourceCurrency, wantedCurrency);
            HistoricalRecord historicalRecord = new HistoricalRecord(new Date(), sourceCurrency, wantedCurrency, value);
            historicalRecordService.create(historicalRecord);
            log.info("Writing");
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage());
        }

        try {
            historicalRecordList = getHistoricalRecordList();
        } catch (Exception e) {

        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("historicalRecordList", historicalRecordList);
        modelAndView.addObject("value", sourceCurrency + " -> " + value.toString() + " " + wantedCurrency);
        modelAndView.addObject("allCurrencies", currencyList);
        modelAndView.setViewName("check");
        return modelAndView;
    }

    private List<HistoricalRecord> getHistoricalRecordList() {
        return historicalRecordService.getLastHistoricalRecords(10);
    }
}
