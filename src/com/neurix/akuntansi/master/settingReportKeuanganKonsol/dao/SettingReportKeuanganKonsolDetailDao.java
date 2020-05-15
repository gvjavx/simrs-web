package com.neurix.akuntansi.master.settingReportKeuanganKonsol.dao;

import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.ImAkunSettingReportKeuanganKonsolDetail;
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
public class SettingReportKeuanganKonsolDetailDao extends GenericDao<ImAkunSettingReportKeuanganKonsolDetail, String> {

    @Override
    protected Class<ImAkunSettingReportKeuanganKonsolDetail> getEntityClass() {
        return ImAkunSettingReportKeuanganKonsolDetail.class;
    }

    @Override
    public List<ImAkunSettingReportKeuanganKonsolDetail> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImAkunSettingReportKeuanganKonsolDetail.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("setting_report_konsol_id") != null){
                criteria.add(Restrictions.eq("settingReportKonsolId", mapCriteria.get("setting_report_konsol_id").toString()));
            }
            if (mapCriteria.get("setting_report_konsol_detail_id") != null){
                criteria.add(Restrictions.eq("settingReportKonsolDetailId", (String)mapCriteria.get("setting_report_konsol_detail_id")));
            }
            if(mapCriteria.get("rekening_id") != null){
                criteria.add(Restrictions.eq("rekeningId",  mapCriteria.get("rekening_id").toString()));
            }
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }

        // Order by
        criteria.addOrder(Order.desc("settingReportKonsolDetailId"));
        List<ImAkunSettingReportKeuanganKonsolDetail> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTransId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_setting_report_keuangan_konsol_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return sId;
    }
}