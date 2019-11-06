package com.neurix.hris.transaksi.absensi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.absensi.model.AbsensiDashboardEntity;
import com.neurix.hris.transaksi.absensi.model.AbsensiDashboardEntity;
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
public class AbsensiDashboardDao extends GenericDao<AbsensiDashboardEntity, String> {
    @Override
    protected Class<AbsensiDashboardEntity> getEntityClass() {
        return AbsensiDashboardEntity.class;
    }

    @Override
    public List<AbsensiDashboardEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    public String getNextAbsensiDashboard() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_absensi_dashboard')");
        Iterator<BigInteger> iter=query.list().iterator();
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        DateFormat dm = new SimpleDateFormat("MM"); // Just the Month, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        String formattedMonth = dm.format(Calendar.getInstance().getTime());
        String sId = String.format("%06d", iter.next());

        return "AD"+formattedDate+formattedMonth+sId;
    }
}
