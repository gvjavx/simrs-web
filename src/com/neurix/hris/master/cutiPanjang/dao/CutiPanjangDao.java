
package com.neurix.hris.master.cutiPanjang.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.cutiPanjang.model.ImCutiPanjangEntity;
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
public class CutiPanjangDao extends GenericDao<ImCutiPanjangEntity, String> {

    @Override
    protected Class<ImCutiPanjangEntity> getEntityClass() {
        return ImCutiPanjangEntity.class;
    }

    @Override
    public List<ImCutiPanjangEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImCutiPanjangEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("cutiPanjang_id")!=null) {
                criteria.add(Restrictions.eq("cutiPanjangId", (String) mapCriteria.get("cutiPanjang_id")));
            }
            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("tipe_hari")!=null) {
                criteria.add(Restrictions.eq("tipeHari", (String) mapCriteria.get("tipe_hari")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("cutiPanjangId"));

        List<ImCutiPanjangEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextCutiPanjangId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_cuti_panjang')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "CTP"+sId;
    }


    public List<ImCutiPanjangEntity> getListcutiPanjang(String term) throws HibernateException {

        List<ImCutiPanjangEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImCutiPanjangEntity.class)
                .add(Restrictions.ilike("cutiPanjangName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("cutiPanjangId"))
                .list();

        return results;
    }

public List<ImCutiPanjangEntity> getListCutiPanjang(Map mapCriteria) throws HibernateException {
    Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImCutiPanjangEntity.class);
    // Get Collection and sorting
    if (mapCriteria!=null) {
        if (mapCriteria.get("cuti_panjang_id")!=null) {
            criteria.add(Restrictions.eq("cutiPanjangId", (String) mapCriteria.get("cuti_panjang_id")));
        }
        if (mapCriteria.get("golongan_id")!=null) {
            criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
        }
        if (mapCriteria.get("branch_id")!=null) {
            criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branchId")));
        }
    }



    criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

    // Order by
    criteria.addOrder(Order.asc("cutiPanjangId"));

    List<ImCutiPanjangEntity> results = criteria.list();

    return results;
//    List<ImCutiPanjangEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImCutiPanjangEntity.class)
//            .add(Restrictions.eq("cutiPanjangId", term))
//            .addOrder(Order.asc("cutiPanjangId"))
//            .list();
//
//    return results;
}
    public List<ImCutiPanjangEntity> getListCutiPanjangBybranch(String term) throws HibernateException {
        List<ImCutiPanjangEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImCutiPanjangEntity.class)
                .add(Restrictions.eq("branchId", term))
                .addOrder(Order.asc("cutiPanjangId"))
                .list();
        return results;
    }
    public List<ImCutiPanjangEntity> getListCutiPanjangBygolongan(String term) throws HibernateException {
        List<ImCutiPanjangEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImCutiPanjangEntity.class)
                .add(Restrictions.eq("golonganId", term))
                .addOrder(Order.asc("cutiPanjangId"))
                .list();
        return results;
    }
    public List<ImCutiPanjangEntity> getListCutiPanjangBygolonganAndBranch(String term,String branchId) throws HibernateException {
        List<ImCutiPanjangEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImCutiPanjangEntity.class)
                .add(Restrictions.eq("golonganId", term))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag","Y"))
                .addOrder(Order.asc("cutiPanjangId"))
                .list();
        return results;
    }
    public List<ImCutiPanjangEntity> getListCutiPanjangById(String term) throws HibernateException {
        List<ImCutiPanjangEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImCutiPanjangEntity.class)
                .add(Restrictions.eq("golonganId", term))
                .addOrder(Order.asc("cutiPanjangId"))
                .list();
        return results;
    }

    public String cekResetCutiPanjang(String nip){
        String listOfResult="";
        String query= "select distinct on(nip) nip, tanggal_dari, keterangan from it_hris_cuti_pegawai\n" +
                "where keterangan ilike 'RESET PANJANG'\n" +
                "and nip ='"+nip+"'";

        List<Object[]> results ;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).list();

        if (results.size()>0){
            for (Object[] rows: results){
                String[] tahun = rows[1].toString().split("-");
                listOfResult = tahun[0];
            }
        }
        else{
            listOfResult=null;
        }
        return listOfResult;
    }

}
