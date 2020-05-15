package com.neurix.akuntansi.transaksi.pengajuanBiaya.dao;

import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.ImPengajuanBiayaEntity;
import com.neurix.common.dao.GenericDao;
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
public class PengajuanBiayaDao extends GenericDao<ImPengajuanBiayaEntity, String> {

    @Override
    protected Class<ImPengajuanBiayaEntity> getEntityClass() {
        return ImPengajuanBiayaEntity.class;
    }

    @Override
    public List<ImPengajuanBiayaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPengajuanBiayaEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pengajuan_biaya_id")!=null) {
                criteria.add(Restrictions.eq("pengajuanBiayaId", (String) mapCriteria.get("pengajuan_biaya_id")));
            }
            if (mapCriteria.get("no_jurnal")!=null) {
                criteria.add(Restrictions.eq("noJurnal", (String) mapCriteria.get("no_jurnal")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("pengajuanBiayaId"));
        List<ImPengajuanBiayaEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPengajuanBiayaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengajuan_biaya')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PB"+sId;
    }

}
