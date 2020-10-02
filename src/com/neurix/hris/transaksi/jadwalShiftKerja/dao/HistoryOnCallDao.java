package com.neurix.hris.transaksi.jadwalShiftKerja.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.ItHrisHistoryOnCallEntity;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalShiftKerjaDetail;
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
public class HistoryOnCallDao extends GenericDao<ItHrisHistoryOnCallEntity, String> {

    @Override
    protected Class<ItHrisHistoryOnCallEntity> getEntityClass() {
        return ItHrisHistoryOnCallEntity.class;
    }

    @Override
    public List<ItHrisHistoryOnCallEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHrisHistoryOnCallEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("jadwal_shift_kerja_id")!=null) {
                criteria.add(Restrictions.eq("jadwalShiftKerjaId", (String) mapCriteria.get("jadwal_shift_kerja_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("historyOnCallId"));

        List<ItHrisHistoryOnCallEntity> results = criteria.list();

        return results;
    }
    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_history_on_call')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "HOC"+sId;
    }

    public List<ItHrisHistoryOnCallEntity> getByJadwalIdAndNip(String jadwalId,String nip) throws HibernateException {
        List<ItHrisHistoryOnCallEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisHistoryOnCallEntity.class)
                .add(Restrictions.eq("jadwalShiftKerjaDetailId",jadwalId))
                .add(Restrictions.eq("nip",nip))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}
