package com.neurix.hris.transaksi.absensi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiDetailEntity;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiEntity;
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
public class MesinAbsensiDetailDao extends GenericDao<MesinAbsensiDetailEntity, String> {
    @Override
    protected Class<MesinAbsensiDetailEntity> getEntityClass() {
        return MesinAbsensiDetailEntity.class;
    }

    @Override
    public List<MesinAbsensiDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(MesinAbsensiDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("mesin_absensi_detail_id")!=null) {
                criteria.add(Restrictions.eq("mesinAbsensiDetailId", (String) mapCriteria.get("mesin_absensi_detail_id")));
            }
            if (mapCriteria.get("pin")!=null) {
                criteria.add(Restrictions.eq("pin", (String) mapCriteria.get("pin")));
            }
            if (mapCriteria.get("status")!=null) {
                criteria.add(Restrictions.eq("status", (String) mapCriteria.get("status")));
            }
            if (mapCriteria.get("verify_mode")!=null) {
                criteria.add(Restrictions.eq("verifyMode", (String) mapCriteria.get("verify_mode")));
            }
            if (mapCriteria.get("work_code")!=null) {
                criteria.add(Restrictions.eq("workCode", (String) mapCriteria.get("work_code")));
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

        List<MesinAbsensiDetailEntity> results = criteria.list();

        return results;
    }

    public List<MesinAbsensiDetailEntity> getAllDetailWithDate(Timestamp tanggalAwal, Timestamp tanggalAkhir) throws HibernateException {
        List<MesinAbsensiDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(MesinAbsensiDetailEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.ge("scanDate", tanggalAwal))
                .add(Restrictions.lt("scanDate", tanggalAkhir))
                .addOrder(Order.desc("pin"))
                .list();
        return results;
    }
    public List<MesinAbsensiDetailEntity> getAllDetailWithDateAndPin(String pin,Timestamp tanggalAwal, Timestamp tanggalAkhir,String branchId) throws HibernateException {
        List<MesinAbsensiDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(MesinAbsensiDetailEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("pin", pin))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.ge("scanDate", tanggalAwal))
                .add(Restrictions.lt("scanDate", tanggalAkhir))
                .addOrder(Order.asc("scanDate"))
                .list();
        return results;
    }

    public String getNextMesinAbsensiDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_mesin_absensi_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        DateFormat dm = new SimpleDateFormat("MM"); // Just the Month, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        String formattedMonth = dm.format(Calendar.getInstance().getTime());
        String sId = String.format("%06d", iter.next());

        return "MAD"+formattedDate+formattedMonth+sId;
    }
}
