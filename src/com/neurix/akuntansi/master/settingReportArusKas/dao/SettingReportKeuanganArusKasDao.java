package com.neurix.akuntansi.master.settingReportArusKas.dao;

import com.neurix.akuntansi.master.settingReportArusKas.model.ImAkunSettingReportArusKasEntity;
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
public class SettingReportKeuanganArusKasDao extends GenericDao<ImAkunSettingReportArusKasEntity, String> {

    @Override
    protected Class<ImAkunSettingReportArusKasEntity> getEntityClass() {
        return ImAkunSettingReportArusKasEntity.class;
    }

    @Override
    public List<ImAkunSettingReportArusKasEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImAkunSettingReportArusKasEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("setting_report_arus_kas_id") != null){
                criteria.add(Restrictions.eq("settingReportArusKasId", mapCriteria.get("setting_report_arus_kas_id").toString()));
            }
            if (mapCriteria.get("nama_kode_rekening_alias") != null){
                criteria.add(Restrictions.ilike("namaKodeRekeningAlias", "%" + (String)mapCriteria.get("nama_kode_rekening_alias") + "%"));
            }
            if(mapCriteria.get("kode_rekening_alias") != null){
                criteria.add(Restrictions.eq("kodeRekeningAlias",  mapCriteria.get("kode_rekening_alias").toString()));
            }
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }

        // Order by
        criteria.addOrder(Order.desc("settingReportArusKasId"));
        List<ImAkunSettingReportArusKasEntity> results = criteria.list();

        return results;
    }

    public List<ImAkunSettingReportArusKasEntity> listReportKeuanganArusKas(){
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImAkunSettingReportArusKasEntity.class);
        criteria.add(Restrictions.eq("flag","Y"));
        criteria.addOrder(Order.asc("kodeRekeningAlias"));
        return  criteria.list();
    }
    public String getCoaAliasNameByCoaAlias(String coaAlias){
        String result = "";
        String query="select \n" +
                "\tnama_kode_rekening_alias \n" +
                "from\n" +
                "\tim_akun_setting_report_arus_kas\n" +
                "where\n" +
                "\tkode_rekening_alias='"+coaAlias+"'\n" +
                "\tand flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result = results.toString();
        }
        return result;
    }


    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_report_arus_kas')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "RAK" + sId;
    }

    public String getNextDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_report_arus_kas_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "RAKD" + sId;
    }
}
