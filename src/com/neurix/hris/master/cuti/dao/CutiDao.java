package com.neurix.hris.master.cuti.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.cuti.model.ImCutiEntity;
import com.neurix.hris.master.cuti.model.ImCutiHistoryEntity;
import com.neurix.hris.transaksi.cutiPegawai.model.ItCutiPegawaiEntity;
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
public class CutiDao extends GenericDao<ImCutiEntity, String> {

    @Override
    protected Class<ImCutiEntity> getEntityClass() {
        return ImCutiEntity.class;
    }

    @Override
    public List<ImCutiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImCutiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("cuti_id")!=null) {
                criteria.add(Restrictions.eq("cutiId", (String) mapCriteria.get("cuti_id")));
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

            if (mapCriteria.get("cuti_name")!=null) {
                criteria.add(Restrictions.ilike("cutiName", "%" + (String)mapCriteria.get("cuti_name") + "%"));
            }

            //RAKA-02FEB2021==>Memasukkan Cuti Diluar Tanggungan
//            if (mapCriteria.get("jenis_cuti") != null){
//                criteria.add(Restrictions.ne("cutiId", "CT007"));
//            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("cutiId"));

        List<ImCutiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextCutiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_cuti')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "CT"+sId;
    }

    public String getNextCutiHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_cuti_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "HC"+sId;
    }

    public List<ImCutiEntity> getListCuti(String term) throws HibernateException {

        List<ImCutiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImCutiEntity.class)
                .add(Restrictions.ilike("cutiName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("cutiId"))
                .list();

        return results;
    }

    public void addAndSaveHistory(ImCutiHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    //get list tipe
    public List<ImCutiEntity> getListCutiTipe(String term) throws HibernateException {

        List<ImCutiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImCutiEntity.class)
                .add(Restrictions.eq("cutiId",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();

        return results;
    }

}
