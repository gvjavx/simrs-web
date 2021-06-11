package com.neurix.hris.transaksi.absensi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiDetailEntity;
import com.neurix.hris.transaksi.absensi.model.PegawaiTambahanAbsensiEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
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
public class PegawaiTambahanAbsensiDao extends GenericDao<PegawaiTambahanAbsensiEntity, String> {
    @Override
    protected Class<PegawaiTambahanAbsensiEntity> getEntityClass() {
        return PegawaiTambahanAbsensiEntity.class;
    }

    @Override
    public List<PegawaiTambahanAbsensiEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    public List<PegawaiTambahanAbsensiEntity> searchExistingAbsensi(String pin , Date tanggal){
        List<PegawaiTambahanAbsensiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(PegawaiTambahanAbsensiEntity.class)
                .add(Restrictions.eq("pin", pin))
                .add(Restrictions.eq("tanggal", tanggal))
                .add(Restrictions.eq("flag","Y"))
                .list();
        return results;
    }

    public String getNextPegawaiTambahanAbsensiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pegawai_tambahan_absensi')");
        Iterator<BigInteger> iter=query.list().iterator();
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        DateFormat dm = new SimpleDateFormat("MM"); // Just the Month, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        String formattedMonth = dm.format(Calendar.getInstance().getTime());
        String sId = String.format("%07d", iter.next());

        return "PTA"+formattedDate+formattedMonth+sId;
    }

    public List<PegawaiTambahanAbsensiEntity> getByCriteriaForReportUangMakan(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(PegawaiTambahanAbsensiEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pin")!=null) {
                criteria.add(Restrictions.eq("pin", (String) mapCriteria.get("pin")));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.between("tanggal",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("tanggal",mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai")!=null) {
                    criteria.add(Restrictions.le("tanggal",mapCriteria.get("tanggal_selesai")));
                }
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("tanggal"));

        List<PegawaiTambahanAbsensiEntity> results = criteria.list();

        return results;
    }
}
