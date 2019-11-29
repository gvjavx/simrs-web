package com.neurix.simrs.transaksi.periksaradiologi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.periksaradiologi.model.ItSimrsPeriksaRadiologiEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PeriksaRadiologiDao extends GenericDao<ItSimrsPeriksaRadiologiEntity, String> {
    @Override
    protected Class<ItSimrsPeriksaRadiologiEntity> getEntityClass() {
        return ItSimrsPeriksaRadiologiEntity.class;
    }

    @Override
    public List<ItSimrsPeriksaRadiologiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPeriksaRadiologiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_periksa_radiologi")!=null) {
                criteria.add(Restrictions.eq("idPeriksaRadiologi", (String) mapCriteria.get("id_periksa_radiologi")));
            }
            if (mapCriteria.get("id_dokter_radiologi")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_dokter_radiologi")));
            }
            if (mapCriteria.get("id_lab")!=null) {
                criteria.add(Restrictions.eq("idLab", (String) mapCriteria.get("id_lab")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailChekup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("id_periksa_lab")!=null) {
                criteria.add(Restrictions.eq("idPeriksaLab", (String) mapCriteria.get("id_periksa_lab")));
            }
            if (mapCriteria.get("status")!=null) {
                criteria.add(Restrictions.eq("statusPeriksa", (String) mapCriteria.get("status")));
            }
            if (mapCriteria.get("pemeriksaan")!=null) {
                criteria.add(Restrictions.eq("pemeriksaan", (String) mapCriteria.get("pemeriksaan")));
            }
            if (mapCriteria.get("kesimpulan")!=null) {
                criteria.add(Restrictions.eq("kesimpulan", (String) mapCriteria.get("kesimpulan")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("createdDate"));

        List<ItSimrsPeriksaRadiologiEntity> results = criteria.list();
        return results;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_periksa_radiologi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }


}