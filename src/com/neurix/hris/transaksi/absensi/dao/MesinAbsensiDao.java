package com.neurix.hris.transaksi.absensi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawaiEntity;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiEntity;
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
public class MesinAbsensiDao extends GenericDao<MesinAbsensiEntity, String> {

    @Override
    protected Class<MesinAbsensiEntity> getEntityClass() {
        return MesinAbsensiEntity.class;
    }

    @Override
    public List<MesinAbsensiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(MesinAbsensiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("absensi_id")!=null) {
                criteria.add(Restrictions.eq("absensiId", (String) mapCriteria.get("absensi_id")));
            }
            if (mapCriteria.get("pin")!=null) {
                criteria.add(Restrictions.eq("pin", (String) mapCriteria.get("pin")));
            }
            if (mapCriteria.get("tanggal")!=null) {
                criteria.add(Restrictions.eq("tanggal",mapCriteria.get("tanggal")));
            }
            if (mapCriteria.get("status")!=null) {
                criteria.add(Restrictions.eq("status", (String) mapCriteria.get("status")));
            }
            if (mapCriteria.get("flag_absensi")!=null) {
                criteria.add(Restrictions.eq("flagAbsensi", (String) mapCriteria.get("flag_absensi")));
            }
            if (mapCriteria.get("jam_masuk")!=null) {
                criteria.add(Restrictions.eq("jamMasuk", (String) mapCriteria.get("jam_masuk")));
            }
            if (mapCriteria.get("jam_keluar")!=null) {
                criteria.add(Restrictions.eq("jamKeluar", (String) mapCriteria.get("jam_keluar")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("absensiId"));

        List<MesinAbsensiEntity> results = criteria.list();

        return results;
    }

    public String getNextMesinAbsensiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_mesin_absensi')");
        Iterator<BigInteger> iter=query.list().iterator();
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        DateFormat dm = new SimpleDateFormat("MM"); // Just the Month, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        String formattedMonth = dm.format(Calendar.getInstance().getTime());
        String sId = String.format("%05d", iter.next());

        return "MA"+formattedDate+formattedMonth+sId;
    }
}
