package manuelgonzalo.zooplustest.currency.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by manuel on 26/6/17.
 */
@Entity
@Table(name = "historical_record")
public class HistoricalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="record_time")
    private Date recordTime;
    @Column(name="source_currency")
    private String sourceCurrency;
    @Column(name="wanted_currency")
    private String wantedCurrency;
    @Column(name="rate")
    private Float rate;

    public HistoricalRecord() {

    }

    public HistoricalRecord(Date recordTime,  String sourceCurrency, String wantedCurrency, Float rate) {
        this.recordTime = recordTime;
        this.sourceCurrency = sourceCurrency;
        this.wantedCurrency = wantedCurrency;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getWantedCurrency() {
        return wantedCurrency;
    }

    public void setWantedCurrency(String wantedCurrency) {
        this.wantedCurrency = wantedCurrency;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}
