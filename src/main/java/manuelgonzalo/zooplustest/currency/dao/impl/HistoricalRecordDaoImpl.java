package manuelgonzalo.zooplustest.currency.dao.impl;

import manuelgonzalo.zooplustest.currency.dao.HistoricalRecordDao;
import manuelgonzalo.zooplustest.currency.model.HistoricalRecord;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by manuel on 26/6/17.
 */
@Repository
public class HistoricalRecordDaoImpl implements HistoricalRecordDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(HistoricalRecord historicalRecord) throws DataAccessException {
        entityManager.persist(historicalRecord);
    }

    @Override
    @Transactional
    public List<HistoricalRecord> getLastHistoricalRecords(int number) throws DataAccessException {
        Query query = entityManager.createQuery("select hr from HistoricalRecord hr order by hr.id desc");
        List<HistoricalRecord> resultList = query.setMaxResults(number).getResultList();
        return resultList;
    }

}
