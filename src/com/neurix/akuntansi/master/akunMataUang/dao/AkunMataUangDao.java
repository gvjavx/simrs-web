package com.neurix.akuntansi.master.akunMataUang.dao;

import com.neurix.akuntansi.master.akunMataUang.model.ImAkunMataUangEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Aji Noor on 05/02/2021.
 */

public class AkunMataUangDao extends GenericDao<ImAkunMataUangEntity, String> {

    @Override
    protected Class<ImAkunMataUangEntity> getEntityClass() {
        return ImAkunMataUangEntity.class;
    }

    @Override
    public List<ImAkunMataUangEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImAkunMataUangEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("mata_uang_id")!=null) {
                criteria.add(Restrictions.eq("mataUangId", (String) mapCriteria.get("mata_uang_id")));
            }
            if (mapCriteria.get("nama_mata_uang")!=null) {
                criteria.add(Restrictions.ilike("namaMataUang", "%" + (String)mapCriteria.get("nama_mata_uang") + "%"));
            }
            if (mapCriteria.get("kode_mata_uang")!=null) {
                criteria.add(Restrictions.eq("kodeMataUang", (String) mapCriteria.get("kode_mata_uang")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }
        List<ImAkunMataUangEntity> results = criteria.list();
        return results;
    }

    // Generate next id from postgre
    public String getNextMataUangId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_mata_uang')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%010d", iter.next());
        return sId;
    }

    public List<ImAkunMataUangEntity> getMataUangByKode(String kodeMataUang) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImAkunMataUangEntity.class);
        criteria.add(Restrictions.eq("kodeMataUang", kodeMataUang));
        criteria.add(Restrictions.eq("flag", "Y"));
        List<ImAkunMataUangEntity> results = criteria.list();
        return results;
    }


    public List<ImAkunMataUangEntity> getCurrencyActive() throws HibernateException {


        String query = "select * from im_akun_mata_uang where flag = :flag";

        List<Object[]> results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("flag", "Y")
                .list();

        List<ImAkunMataUangEntity> listMataUang = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                ImAkunMataUangEntity akunMataUang = new ImAkunMataUangEntity();
                akunMataUang.setMataUangId(obj[0].toString());
                akunMataUang.setNamaMataUang(obj[2].toString());
                listMataUang.add(akunMataUang);
            }
        }
        return listMataUang;
    }

}
