
package com.neurix.hris.master.provinsi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.provinsi.model.ImDesaEntity;
import com.neurix.hris.master.provinsi.model.ImKecamatanEntity;
import com.neurix.hris.master.provinsi.model.ImProvinsiEntity;
import com.neurix.hris.master.provinsi.model.ImKotaEntity;

import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
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
public class ProvinsiDao extends GenericDao<ImProvinsiEntity, String> {

    @Override
    protected Class<ImProvinsiEntity> getEntityClass() {
        return ImProvinsiEntity.class;
    }

    @Override
    public List<ImProvinsiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImProvinsiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("provinsi_id")!=null) {
                criteria.add(Restrictions.eq("provinsiId", (String) mapCriteria.get("provinsi_id")));
            }
            if (mapCriteria.get("provinsi_name")!=null) {
                criteria.add(Restrictions.ilike("provinsiName", "%" + (String)mapCriteria.get("provinsi_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("provinsiId"));

        List<ImProvinsiEntity> results = criteria.list();

        return results;
    }

    public List<ImKotaEntity> getByCriteriaKota(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImKotaEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("kota_id")!=null) {
                criteria.add(Restrictions.eq("kotaId", (String) mapCriteria.get("provinsi_id")));
            }
            if (mapCriteria.get("kota_name")!=null) {
                criteria.add(Restrictions.ilike("kotaName", "%" + (String)mapCriteria.get("kota_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("kotaId"));

        List<ImKotaEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextProvinsiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_provinsi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "D"+sId;
    }

    public String getNextProvinsiHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_provinsi_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ImProvinsiEntity> getListProvinsi(String term) throws HibernateException {

        List<ImProvinsiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImProvinsiEntity.class)
                .add(Restrictions.ilike("provinsiName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("provinsiId"))
                .list();

        return results;
    }

    public List<ImKotaEntity> getListKota(String term, String prov) throws HibernateException {

        List<ImKotaEntity> results = null ;
        if(prov.equals("")){
            results = this.sessionFactory.getCurrentSession().createCriteria(ImKotaEntity.class)
                    .add(Restrictions.ilike("kotaName",term.toUpperCase()))
                    .add(Restrictions.eq("flag", "Y"))
                    .addOrder(Order.asc("kotaId"))
                    .list();
        }else{
            results = this.sessionFactory.getCurrentSession().createCriteria(ImKotaEntity.class)
                    .add(Restrictions.ilike("kotaName",term.toUpperCase()))
                    .add(Restrictions.eq("flag", "Y"))
                    .add(Restrictions.eq("provinsiId", prov))
                    .addOrder(Order.asc("kotaId"))
                    .list();
        }

        return results;
    }

    public List<ImKecamatanEntity> getListKecamatan(String term, String kota) throws HibernateException {

        List<ImKecamatanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImKecamatanEntity.class)
                .add(Restrictions.ilike("kecamatanName",term.toUpperCase()))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("kotaId", kota))
                .addOrder(Order.asc("kecamatanId"))
                .list();

        return results;
    }

    public List<ImProvinsiEntity> getListProvinsiById(String term) throws HibernateException {

        List<ImProvinsiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImProvinsiEntity.class)
                .add(Restrictions.ilike("provinsiId",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("provinsiId"))
                .list();

        return results;
    }

    public List<ImKotaEntity> getListKotaById(String term) throws HibernateException {
        List<ImKotaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImKotaEntity.class)
                .add(Restrictions.eq("kotaId",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kotaId"))
                .list();
        return results;
    }

    public List<ImKecamatanEntity> getListKecamatanById(String term) throws HibernateException {
        List<ImKecamatanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImKecamatanEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("kecamatanId", term))
                .addOrder(Order.asc("kecamatanId"))
                .list();

        return results;
    }

    public List<ImDesaEntity> getListDesaById(String term) throws HibernateException {

        List<ImDesaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImDesaEntity.class)
                .add(Restrictions.ilike("desaId",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("desaId"))
                .list();

        return results;
    }
    public List<ImDesaEntity> getListDesa(String term, String kota) throws HibernateException {

        List<ImDesaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImDesaEntity.class)
                .add(Restrictions.ilike("desaName",term.toUpperCase()))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("kecamatanId", kota))
                .addOrder(Order.asc("desaId"))
                .list();

        return results;
    }

    public List<Object[]> getListAlamatByDesaId(String desaId){
        String SQL = "SELECT \n" +
                "ds.desa_name, \n" +
                "kec.kecamatan_name,\n" +
                "kot.kota_name,\n" +
                "prov.provinsi_name,\n" +
                "kec.kecamatan_id,\n" +
                "kot.kota_id,\n" +
                "prov.provinsi_id\n" +
                "FROM \n" +
                "im_hris_desa ds\n" +
                "INNER JOIN im_hris_kecamatan kec ON kec.kecamatan_id = ds.kecamatan_id\n" +
                "INNER JOIN im_hris_kota kot ON kot.kota_id = kec.kota_id\n" +
                "INNER JOIN im_hris_provinsi prov ON prov.provinsi_id = kot.provinsi_id\n" +
                "WHERE ds.desa_id = :id ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", desaId)
                .list();

        return results;
    }

}
