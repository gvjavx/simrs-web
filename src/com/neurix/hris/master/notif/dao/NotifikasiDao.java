package com.neurix.hris.master.notif.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.notif.model.ItNotifikasiEntity;
import com.neurix.hris.master.notif.model.Notif;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class NotifikasiDao extends GenericDao<ItNotifikasiEntity,String> {
//    Connection conn;
//
//    public Connection getConn() {
//        return conn;
//    }
//
//    public void setConn(Connection conn) {
//        this.conn = conn;
//    }

    @Override
    protected Class<ItNotifikasiEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ItNotifikasiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItNotifikasiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("notif_id")!=null) {
                criteria.add(Restrictions.eq("notifId", (String) mapCriteria.get("notif_id")));
            }
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.ilike("nip", "%" + (String)mapCriteria.get("nip") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("notifId"));

        List<ItNotifikasiEntity> results = criteria.list();

        return results;
    }

    public List<Notif> getJumlahNotif() throws HibernateException, SQLException {
        List<Notif> listOfResult = new ArrayList<Notif>();
            String jml = "";
            String query;
//            Integer count = null;
//
//            Statement stmt3 = conn.createStatement();
//            ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(notif_id) as jml FROM im_notif WHERE flag = 'Y'");
//            while(rs3.next()){
//                count = rs3.getInt("jml");
//            }
//
//        if (count != null){
//            Notif notif  = new Notif();
//            String stCount = count.toString();
//            notif.setJml(stCount);
//            listOfResult.add(notif);
//        }





            query = "SELECT COUNT(notif_id) as jml FROM im_notif\n" +
                    "WHERE flag = 'Y' ";

            List<Object[]> results = new ArrayList<Object[]>();

            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            Notif notif;
            for (Object[] row : results) {
                notif = new Notif();
                String stJml = row[0].toString();
                notif.setJml(stJml);
            }
        return listOfResult;
    }



    // Generate surrogate id from postgre
    public String getNextNotifId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_notifikasi')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }

//    // Generate surrogate id from postgre
//    public String getNextGoupHistoryId() throws HibernateException {
//        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_group_history')");
//        Iterator<BigInteger> iter=query.list().iterator();
//        return String.valueOf(iter.next().longValue());
//    }
//
//
//    public void addAndSaveHistory(ItHrisTrainingDocEntity entity) throws HibernateException {
//        this.sessionFactory.getCurrentSession().save(entity);
//    }
}
