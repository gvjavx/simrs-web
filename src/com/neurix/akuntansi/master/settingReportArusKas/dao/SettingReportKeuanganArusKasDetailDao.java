package com.neurix.akuntansi.master.settingReportArusKas.dao;

import com.neurix.akuntansi.master.settingReportArusKas.model.ImAkunSettingReportArusKasDetailEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
public class SettingReportKeuanganArusKasDetailDao extends GenericDao<ImAkunSettingReportArusKasDetailEntity, String> {

    @Override
    protected Class<ImAkunSettingReportArusKasDetailEntity> getEntityClass() {
        return ImAkunSettingReportArusKasDetailEntity.class;
    }

    @Override
    public List<ImAkunSettingReportArusKasDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImAkunSettingReportArusKasDetailEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("setting_report_arus_kas_id") != null){
                criteria.add(Restrictions.eq("settingReportArusKasId", mapCriteria.get("setting_report_arus_kas_id").toString()));
            }
            if (mapCriteria.get("setting_report_arus_kas_detail_id") != null){
                criteria.add(Restrictions.eq("settingReportArusKasDetailId", (String)mapCriteria.get("setting_report_arus_kas_detail_id")));
            }
            if(mapCriteria.get("rekening_id") != null){
                criteria.add(Restrictions.eq("rekeningId",  mapCriteria.get("rekening_id").toString()));
            }
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }

        // Order by
        criteria.addOrder(Order.desc("settingReportArusKasDetailId"));
        List<ImAkunSettingReportArusKasDetailEntity> results = criteria.list();

        return results;
    }
}