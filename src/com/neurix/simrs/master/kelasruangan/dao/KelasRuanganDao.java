package com.neurix.simrs.master.kelasruangan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class KelasRuanganDao extends GenericDao<ImSimrsKelasRuanganEntity, String> {
    @Override
    protected Class<ImSimrsKelasRuanganEntity> getEntityClass() {
        return ImSimrsKelasRuanganEntity.class;
    }

    @Override
    public List<ImSimrsKelasRuanganEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKelasRuanganEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_kelas_ruangan")!=null) {
                criteria.add(Restrictions.eq("idKelasRuangan", (String) mapCriteria.get("id_kelas_ruangan")));
            }
            if (mapCriteria.get("nama_kelas_ruangan")!=null) {
                criteria.add(Restrictions.ilike("namaKelasRuangan", "%" + (String)mapCriteria.get("nama_kelas_ruangan") + "%"));
            }
            if (mapCriteria.get("divisi_id")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String)mapCriteria.get("divisi_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idKelasRuangan"));

        List<ImSimrsKelasRuanganEntity> results = criteria.list();

        return results;
    }

    public String getNextIdKelasRuangan(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kelas_ruangan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return sId;
    }

    public List<ImSimrsKelasRuanganEntity> getDataKelasRuangan(String namaKelasRuangan) throws HibernateException {
        List<ImSimrsKelasRuanganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKelasRuanganEntity.class)
                .add(Restrictions.eq("namaKelasRuangan", namaKelasRuangan))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public String getNextKodering() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kodering')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%01d", iter.next());

        return sId;
    }
}