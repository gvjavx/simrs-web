package com.neurix.simrs.transaksi.profilrekammedisrj.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.profilrekammedisrj.model.ItSimrsRekamMedisRawatJalanEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RekamMedisRawatJalanDao extends GenericDao<ItSimrsRekamMedisRawatJalanEntity, String> {

    @Override
    protected Class<ItSimrsRekamMedisRawatJalanEntity> getEntityClass() {
        return ItSimrsRekamMedisRawatJalanEntity.class;
    }

    @Override
    public List<ItSimrsRekamMedisRawatJalanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRekamMedisRawatJalanEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_profil_rekam_medis_rj")!=null) {
                criteria.add(Restrictions.eq("idProfilRekamMedisRj", (String) mapCriteria.get("id_profil_rekam_medis_rj")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.desc("idProfilRekamMedisRj"));

        List<ItSimrsRekamMedisRawatJalanEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_profil_rj')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}