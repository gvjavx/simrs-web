package com.neurix.akuntansi.master.settingReportUser.dao;

import com.neurix.akuntansi.master.settingReportUser.model.ImSettingReportUserEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.access.method.P;

import java.math.BigInteger;
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
public class SettingReportUserDao extends GenericDao<ImSettingReportUserEntity, String> {

    @Override
    protected Class<ImSettingReportUserEntity> getEntityClass() {
        return ImSettingReportUserEntity.class;
    }

    @Override
    public List<ImSettingReportUserEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSettingReportUserEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("setting_report_user_id")!=null) {
                criteria.add(Restrictions.eq("settingReportUserId", (String) mapCriteria.get("setting_report_user_id")));
            }
            if (mapCriteria.get("report_id")!=null) {
                criteria.add(Restrictions.eq("reportId", (String) mapCriteria.get("report_id")));
            }
            if (mapCriteria.get("user_id")!=null) {
                criteria.add(Restrictions.eq("userId", (String) mapCriteria.get("user_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("reportId"));
        List<ImSettingReportUserEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSettingReportUserId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_report_user_setting')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "RU"+sId;
    }

    public List<ImSettingReportUserEntity> getListByReportIdNUserId ( String reportId,String userId){
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSettingReportUserEntity.class);
        criteria.add(Restrictions.eq("userId", userId));
        criteria.add(Restrictions.eq("reportId", reportId));
        criteria.add(Restrictions.eq("flag", "Y"));

        // Order by
        criteria.addOrder(Order.asc("reportId"));
        List<ImSettingReportUserEntity> results = criteria.list();

        return results;
    }
}
