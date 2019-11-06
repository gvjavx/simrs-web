
package com.neurix.hris.transaksi.rekruitmen.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.rekruitmen.model.ImRekruitmenHistoryEntity;
import com.neurix.hris.transaksi.rekruitmen.model.ItRekruitmenKontrakEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class RekruitmenKontrakDao extends GenericDao<ItRekruitmenKontrakEntity, String> {

    @Override
    protected Class<ItRekruitmenKontrakEntity> getEntityClass() {
        return ItRekruitmenKontrakEntity.class;
    }

    @Override
    public List<ItRekruitmenKontrakEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenKontrakEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("rekruitmen_kontrak_id")!=null) {
                criteria.add(Restrictions.eq("rekruitmenKontrakId", (String) mapCriteria.get("rekruitmen_kontrak_id")));
            }
            if (mapCriteria.get("pasal")!=null) {
                criteria.add(Restrictions.eq("pasal", (String) mapCriteria.get("pasal")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("rekruitmenKontrakId"));

        List<ItRekruitmenKontrakEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextRekruitmenKontrakId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekruitmen_kontrak')");
        Iterator<BigInteger> iter=query.list().iterator();
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        String sId = String.format("%06d", iter.next());

        return "RK"+formattedDate+sId;
    }

    public void addAndSaveHistory(ImRekruitmenHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);

    }
}
