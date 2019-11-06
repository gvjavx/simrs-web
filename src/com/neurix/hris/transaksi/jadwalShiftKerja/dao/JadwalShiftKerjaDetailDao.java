package com.neurix.hris.transaksi.jadwalShiftKerja.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.ItJadwalShiftKerjaDetailEntity;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.ItJadwalShiftKerjaEntity;
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
public class JadwalShiftKerjaDetailDao extends GenericDao<ItJadwalShiftKerjaDetailEntity, String> {

    @Override
    protected Class<ItJadwalShiftKerjaDetailEntity> getEntityClass() {
        return ItJadwalShiftKerjaDetailEntity.class;
    }

    @Override
    public List<ItJadwalShiftKerjaDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItJadwalShiftKerjaDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("jadwal_shift_kerja_id")!=null) {
                criteria.add(Restrictions.eq("jadwalShiftKerjaId", (String) mapCriteria.get("jadwal_shift_kerja_id")));
            }
            if (mapCriteria.get("jadwal_shift_kerja_detail_id")!=null) {
                criteria.add(Restrictions.eq("jadwalShiftKerjaDetailId", (String) mapCriteria.get("jadwal_shift_kerja_detail_id")));
            }
            if (mapCriteria.get("shift_group_id")!=null) {
                criteria.add(Restrictions.eq("shiftGroupId", (String) mapCriteria.get("shift_group_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("jadwalShiftKerjaDetailId"));

        List<ItJadwalShiftKerjaDetailEntity> results = criteria.list();

        return results;
    }
    public String getNextJadwalShiftKerjaDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jadwal_shift_kerja_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "JSKD"+sId;
    }
}
