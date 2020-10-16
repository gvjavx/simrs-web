package com.neurix.hris.transaksi.absensi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.absensi.model.ItHrisMesinAbsensiDetailOnCallEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
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
public class MesinAbsensiDetailOnCallDao extends GenericDao<ItHrisMesinAbsensiDetailOnCallEntity, String> {
    @Override
    protected Class<ItHrisMesinAbsensiDetailOnCallEntity> getEntityClass() {
        return ItHrisMesinAbsensiDetailOnCallEntity.class;
    }

    @Override
    public List<ItHrisMesinAbsensiDetailOnCallEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHrisMesinAbsensiDetailOnCallEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("mesin_absensi_detail_on_call_id")!=null) {
                criteria.add(Restrictions.eq("mesinAbsensiDetailOnCallId", (String) mapCriteria.get("mesin_absensi_detail_on_call_id")));
            }
            if (mapCriteria.get("pin")!=null) {
                criteria.add(Restrictions.eq("pin", (String) mapCriteria.get("pin")));
            }
            if (mapCriteria.get("status")!=null) {
                criteria.add(Restrictions.eq("status", (String) mapCriteria.get("status")));
            }
            if (mapCriteria.get("scan_date")!=null) {
                criteria.add(Restrictions.eq("scanDate",mapCriteria.get("scan_date")));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.between("scanDate",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("scanDate",mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai")!=null) {
                    criteria.add(Restrictions.le("scanDate",mapCriteria.get("tanggal_selesai")));
                }
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("scanDate"));

        List<ItHrisMesinAbsensiDetailOnCallEntity> results = criteria.list();

        return results;
    }

    public List<ItHrisMesinAbsensiDetailOnCallEntity> getAllDetailWithDate(Timestamp tanggalAwal, Timestamp tanggalAkhir) throws HibernateException {
        List<ItHrisMesinAbsensiDetailOnCallEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisMesinAbsensiDetailOnCallEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.ge("scanDate", tanggalAwal))
                .add(Restrictions.lt("scanDate", tanggalAkhir))
                .addOrder(Order.desc("pin"))
                .list();
        return results;
    }
    public List<ItHrisMesinAbsensiDetailOnCallEntity> getAllDetailWithDateAndPin(String pin,Timestamp tanggalAwal, Timestamp tanggalAkhir) throws HibernateException {
        List<ItHrisMesinAbsensiDetailOnCallEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisMesinAbsensiDetailOnCallEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("pin", pin))
                .add(Restrictions.ge("scanDate", tanggalAwal))
                .add(Restrictions.lt("scanDate", tanggalAkhir))
                .addOrder(Order.asc("scanDate"))
                .list();
        return results;
    }

    public String getId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_mesin_absensi_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        DateFormat dm = new SimpleDateFormat("MM"); // Just the Month, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        String formattedMonth = dm.format(Calendar.getInstance().getTime());
        String sId = String.format("%06d", iter.next());

        return "MADOC"+formattedDate+formattedMonth+sId;
    }
}
