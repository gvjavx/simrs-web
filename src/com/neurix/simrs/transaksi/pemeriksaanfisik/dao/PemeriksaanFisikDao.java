package com.neurix.simrs.transaksi.pemeriksaanfisik.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.ItSimrsPemeriksaanFisikEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 05/02/20.
 */
public class PemeriksaanFisikDao extends GenericDao<ItSimrsPemeriksaanFisikEntity, String> {
    @Override
    protected Class<ItSimrsPemeriksaanFisikEntity> getEntityClass() {
        return ItSimrsPemeriksaanFisikEntity.class;
    }

    @Override
    public List<ItSimrsPemeriksaanFisikEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPemeriksaanFisikEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }

        if (mapCriteria.get("no_checkup") != null){
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        }

        List<ItSimrsPemeriksaanFisikEntity> result = criteria.list();
        return result;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pemeriksaan_fisik')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
