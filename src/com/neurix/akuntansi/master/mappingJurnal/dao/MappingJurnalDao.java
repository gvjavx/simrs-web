package com.neurix.akuntansi.master.mappingJurnal.dao;

import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class MappingJurnalDao extends GenericDao<ImMappingJurnalEntity, String> {

    @Override
    protected Class<ImMappingJurnalEntity> getEntityClass() {
        return ImMappingJurnalEntity.class;
    }

    @Override
    public List<ImMappingJurnalEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImMappingJurnalEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("mappingJurnal_id")!=null) {
                criteria.add(Restrictions.eq("mappingJurnalId", (String) mapCriteria.get("mappingJurnal_id")));
            }
            if (mapCriteria.get("mappingJurnal_name")!=null) {
                criteria.add(Restrictions.ilike("mappingJurnalName", "%" + (String)mapCriteria.get("mappingJurnal_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("mappingJurnalId"));

        List<ImMappingJurnalEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextMappingJurnalId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_mapping_jurnal')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "IJ"+sId;
    }

    public List<ImMappingJurnalEntity> getListMappingJurnalByTipeJurnalId(String id) throws HibernateException {
        List<ImMappingJurnalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImMappingJurnalEntity.class)
                .add(Restrictions.eq("tipeJurnalId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("mappingJurnalId"))
                .list();
        return results;
    }

    public List<ImMappingJurnalEntity> getMappingByTransId(String transId) throws HibernateException {

        List<ImMappingJurnalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImMappingJurnalEntity.class)
                .add(Restrictions.eq("transId", transId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("mappingJurnalId"))
                .list();

        return results;
    }

    public List<ImMappingJurnalEntity> getMappingByTipeJurnalIdNTransId(String jurnalId,String transId) throws HibernateException {

        List<ImMappingJurnalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImMappingJurnalEntity.class)
                .add(Restrictions.eq("tipeJurnalId", jurnalId))
                .add(Restrictions.eq("transId", transId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("keterangan"))
                .list();

        return results;
    }
    public String tipeJurnalByTransId(String transId){
        String result="";
        String query = "select distinct tipe_jurnal_id from im_akun_mapping_jurnal where trans_id='"+transId+"' and flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result = results.toString();
        }else {
            result=null;
        }
        return result;
    }

    // Generate surrogate id from postgre
    public String getNextInvoiceId(String jurnalId,String branchId) throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_invoice')");
        DateFormat dfTahun = new SimpleDateFormat("yy");
        DateFormat dfBulan = new SimpleDateFormat("MM");
        Date date = new Date(new java.util.Date().getTime());
        String tahun = dfTahun.format(date);
        String bulan = dfBulan.format(date);
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return jurnalId+branchId+bulan+tahun+sId;
    }
}
