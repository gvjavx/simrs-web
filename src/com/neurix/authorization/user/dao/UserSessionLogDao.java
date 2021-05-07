    package com.neurix.authorization.user.dao;

import com.neurix.authorization.user.model.ItUserSessionLog;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 0:07
 * To change this template use File | Settings | File Templates.
 */
public class UserSessionLogDao  extends GenericDao<ItUserSessionLog,Long> {
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    protected Class getEntityClass() {
        return ItUserSessionLog.class;
    }

    @Override
    public List<ItUserSessionLog> getByCriteria(Map mapCriteria) throws HibernateException {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItUserSessionLog.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("user_name")!=null) {
                criteria.add(Restrictions.eq("userName", (String) mapCriteria.get("user_name")));
            }
            if (mapCriteria.get("login_date_from")!=null && mapCriteria.get("login_date_to")!=null) {
                criteria.add(Restrictions.between("loginTimestamp", (Timestamp) mapCriteria.get("login_date_from"), (Timestamp) mapCriteria.get("login_date_to")));
            }
            if (mapCriteria.get("flag")!=null) {
                if ("Y".equalsIgnoreCase((String)mapCriteria.get("flag"))) {
                    criteria.add(Restrictions.isNull("logoutTimestamp"));
                } else {
                    criteria.add(Restrictions.isNotNull("logoutTimestamp"));
                }

            }
        }

        criteria.addOrder(Order.desc("id"));

        List<ItUserSessionLog> results = criteria.list();

        return results;
    }

    public List<ItUserSessionLog> getByCount(Map mapCriteria) throws HibernateException {
        List<ItUserSessionLog> listOfResult = new ArrayList<ItUserSessionLog>();
        String activ    = "";
        String user     = "";
        String dateFrom = "";
        String dateTo   = "";
        String date1    = "";
        String date2    = "";

        if ("Y".equalsIgnoreCase((String)mapCriteria.get("flag"))) {
            activ = " and logout_timestamp IS NULL ";
        }else{
            activ = " and logout_timestamp IS NOT NULL ";
        }

        if (mapCriteria.get("user_name")!=null) {
            user = " and log.user_name = '" +mapCriteria.get("user_name")+ "' ";
        }

        if (mapCriteria.get("login_date_from")!=null) {
            dateFrom = " and log.login_timestamp >= '" +mapCriteria.get("login_date_from")+"' ";
        }

        if (mapCriteria.get("login_date_to")!=null) {
            dateTo = " and log.login_timestamp < '" +mapCriteria.get("login_date_to")+"' ";
        }


        String query = "select \n" +
                "\tlog.user_name,\n" +
                "\tus.user_name as name,\n" +
                "\tcount(log.user_name) as user,\n" +
                "\tmax(log.login_timestamp)\n" +
                "from \n" +
                "\tit_user_session_log as log\n" +
                "left join \n" +
                "\tim_users as us on us.user_id = log.user_name\n" +
                "where\n" +
                "\tlog.user_name IS NOT NULL\n" + user + dateFrom + dateTo +
                "group by\n" +
                "\tlog.user_name,\n" +
                "\tus.user_name";

        /*String query = "select \n" +
                "\tuser_name, \n" +
                "\tcount(user_name) as jumlah \n" +
                "from \n" +
                "\tit_user_session_log \n" + dateTo + dateFrom + activ + user +
                "group by user_name";*/


        List<Object[]> results;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        ItUserSessionLog resultReport;
        for(Object[] rows: results){
            if (rows[1] !=  null){
                resultReport = new ItUserSessionLog();
                resultReport.setUserName(rows[0].toString());
                resultReport.setName(rows[1].toString());
                resultReport.setJumlah(rows[2].toString());
                resultReport.setDateFrom(rows[3].toString());

                listOfResult.add(resultReport);
            }
        }
        return listOfResult;
    }

    public List<ItUserSessionLog> getRecordByCriteria(String sessionId) throws HibernateException {

        List<ItUserSessionLog> results = this.sessionFactory.getCurrentSession().createCriteria(getEntityClass())
                .add(Restrictions.eq("sessionId", sessionId ))
                .list();

        ItUserSessionLog resultItem;
        if (results.size() >= 1) {
            resultItem = (ItUserSessionLog) results.get(0);
        } else {
            resultItem = null;
        }

        return results;
    }

    public boolean getRecordUserStillActive(String userId) throws HibernateException {

        List<ItUserSessionLog> results = this.sessionFactory.getCurrentSession().createCriteria(getEntityClass())
                .add(Restrictions.eq("userName", userId ))
                .add(Restrictions.isNull("logoutTimestamp" ))
                .list();

        if (results.size() >= 1) {
            return true;
        } else {
            return false;
        }

    }

}
