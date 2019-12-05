package com.neurix.simrs.transaksi.teamdokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DokterTeamDao extends GenericDao<ItSimrsDokterTeamEntity, String> {
    @Override
    protected Class<ItSimrsDokterTeamEntity> getEntityClass() {
        return ItSimrsDokterTeamEntity.class;
    }

    @Override
    public List<ItSimrsDokterTeamEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsDokterTeamEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_team_dokter")!=null) {
                criteria.add(Restrictions.eq("idTeamDokter", (String) mapCriteria.get("id_team_dokter")));
            }
            if (mapCriteria.get("id_dokter")!=null) {
                criteria.add(Restrictions.eq("idDokter", (String) mapCriteria.get("id_dokter")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("kategori")!=null) {
                criteria.add(Restrictions.eq("kategori", (String) mapCriteria.get("kategori")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));

        // Order by
        criteria.addOrder(Order.asc("idTeamDokter"));

        List<ItSimrsDokterTeamEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_team_dokter')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}