package com.neurix.akuntansi.master.masterVendor.dao;

import com.neurix.akuntansi.master.masterVendor.model.ImMasterVendorEntity;
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
public class MasterVendorDao extends GenericDao<ImMasterVendorEntity, String> {

    @Override
    protected Class<ImMasterVendorEntity> getEntityClass() {
        return ImMasterVendorEntity.class;
    }

    @Override
    public List<ImMasterVendorEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImMasterVendorEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("nomor_master")!=null) {
                criteria.add(Restrictions.eq("nomorMaster", (String) mapCriteria.get("nomor_master")));
            }
            if (mapCriteria.get("nama")!=null) {
                criteria.add(Restrictions.ilike("nama", "%" + (String)mapCriteria.get("nama") + "%"));
            }
            if (mapCriteria.get("tipe_vendor")!=null) {
                criteria.add(Restrictions.eq("tipeVendor", (String) mapCriteria.get("tipe_vendor")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("nomorMaster"));
        List<ImMasterVendorEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextVendorUmumId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_master')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "01."+sId;
    }

    public String getNextVendorBpjsId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_master_bpjs')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "02."+sId;
    }

    public String getNextVendorSewaLahanId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_master_sewa')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "04."+sId;
    }

    public String getNextVendorLainId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_master_lain')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "99."+sId;
    }

    public Integer cekExistingJurnalDetail(String nomorVendor){
        Integer result=0;
        String query = "SELECT\n" +
                "\tCOUNT(master_id)\n" +
                "FROM\n" +
                "\tit_akun_jurnal_detail\n" +
                "WHERE\n" +
                "\tflag='Y' AND\n" +
                "\tmaster_id='"+nomorVendor+"'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result =Integer.parseInt(results.toString());
        }
        return result;
    }
}
