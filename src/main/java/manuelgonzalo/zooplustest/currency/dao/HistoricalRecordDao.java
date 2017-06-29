package manuelgonzalo.zooplustest.currency.dao;

import manuelgonzalo.zooplustest.currency.model.HistoricalRecord;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by manuel on 26/6/17.
 */
public interface HistoricalRecordDao {
    void create(HistoricalRecord historicalRecord) throws DataAccessException;

    List<HistoricalRecord> getLastHistoricalRecords(int number) throws DataAccessException;

}
