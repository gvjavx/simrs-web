package com.neurix.hris.master.notif.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.group.model.ImHrisGroupEntity;
import com.neurix.hris.master.group.model.ImHrisGroupHistory;
import com.neurix.hris.master.notif.model.ImNotif;
import com.neurix.hris.master.notif.model.Notif;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class NotifDao extends GenericDao<ImNotif,String> {
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
    protected Class<ImNotif> getEntityClass() {
        return null;
    }

    @Override
    public List<ImNotif> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImNotif.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("notif_id")!=null) {
                criteria.add(Restrictions.eq("notifId", (String) mapCriteria.get("notif_id")));
            }
            if (mapCriteria.get("notif_name")!=null) {
                criteria.add(Restrictions.ilike("notifName", "%" + (String)mapCriteria.get("notif_name") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("notifId"));

        List<ImNotif> results = criteria.list();

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



//    // Generate surrogate id from postgre
//    public String getNextGroupId() throws HibernateException {
//        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_group')");
//        Iterator<BigInteger> iter=query.list().iterator();
//        return String.valueOf(iter.next().longValue());
//    }

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
