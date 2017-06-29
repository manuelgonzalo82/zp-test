package manuelgonzalo.zooplustest.currency.service.impl;

import manuelgonzalo.zooplustest.currency.dao.HistoricalRecordDao;
import manuelgonzalo.zooplustest.currency.model.HistoricalRecord;
import manuelgonzalo.zooplustest.currency.service.HistoricalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by manuel on 26/6/17.
 */
@Service
@Transactional
public class HistoricalRecordServiceImpl implements HistoricalRecordService {

    @Autowired
    private HistoricalRecordDao historicalRecordDao;

    @Override
    public void create(HistoricalRecord historicalRecord) {
        historicalRecordDao.create(historicalRecord);
    }

    @Override
    public List<HistoricalRecord> getLastHistoricalRecords(int number) {
        return historicalRecordDao.getLastHistoricalRecords(number);
    }
}
