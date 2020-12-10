package com.neurix.akuntansi.master.settingReportKeuanganKonsol.dao;

import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.ImAkunSettingReportKeuanganKonsol;
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
public class SettingReportKeuanganKonsolDao extends GenericDao<ImAkunSettingReportKeuanganKonsol, String> {

    @Override
    protected Class<ImAkunSettingReportKeuanganKonsol> getEntityClass() {
        return ImAkunSettingReportKeuanganKonsol.class;
    }

    @Override
    public List<ImAkunSettingReportKeuanganKonsol> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImAkunSettingReportKeuanganKonsol.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("setting_report_konsol_id") != null){
                criteria.add(Restrictions.eq("settingReportKonsolId", mapCriteria.get("setting_report_konsol_id").toString()));
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
//        criteria.addOrder(Order.desc("settingReportKonsolId"));
        criteria.addOrder(Order.asc("settingReportKonsolId"));
        List<ImAkunSettingReportKeuanganKonsol> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTransId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_setting_report_keuangan_konsol')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return sId;
    }

    public List<ImAkunSettingReportKeuanganKonsol> listReportKeuanganKonsol(){
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImAkunSettingReportKeuanganKonsol.class);
        criteria.add(Restrictions.eq("flag","Y"));
        criteria.addOrder(Order.asc("kodeRekeningAlias"));
        return  criteria.list();
    }
    public String getCoaAliasNameByCoaAlias(String coaAlias){
        String result = "";
        String query="select \n" +
                "\tnama_kode_rekening_alias \n" +
                "from\n" +
                "\tim_akun_setting_report_keuangan_konsol\n" +
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

    public List<ImAkunSettingReportKeuanganKonsol> getDataPelayanan(String namaKodeAlias) throws HibernateException {
        List<ImAkunSettingReportKeuanganKonsol> results = this.sessionFactory.getCurrentSession().createCriteria(ImAkunSettingReportKeuanganKonsol.class)
                .add(Restrictions.eq("namaKodeRekeningAlias", namaKodeAlias))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public String getNextKeuanganKonsolId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_report_konsol')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "SRK" + sId;
    }

    public String getNextKeuanganKonsolDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_report_konsol_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "SRKD" + sId;
    }
}
