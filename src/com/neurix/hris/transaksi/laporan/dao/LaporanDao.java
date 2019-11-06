
package com.neurix.hris.transaksi.laporan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.laporan.model.ItLaporanEntity;
import com.neurix.hris.transaksi.laporan.model.Laporan;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
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
public class LaporanDao extends GenericDao<ItLaporanEntity, String> {

    @Override
    protected Class<ItLaporanEntity> getEntityClass() {
        return ItLaporanEntity.class;
    }

    @Override
    public List<ItLaporanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItLaporanEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }
        // Order by
        criteria.addOrder(Order.asc("laporanId"));
        List<ItLaporanEntity> results = criteria.list();
        return results;
    }
}
