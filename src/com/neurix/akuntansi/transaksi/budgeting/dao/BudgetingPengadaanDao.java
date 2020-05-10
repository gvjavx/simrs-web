package com.neurix.akuntansi.transaksi.budgeting.dao;

import com.neurix.akuntansi.transaksi.budgeting.model.ItAkunBudgetingPengadaanEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 29/04/20.
 */
public class BudgetingPengadaanDao extends GenericDao<ItAkunBudgetingPengadaanEntity, String> {

    @Override
    protected Class<ItAkunBudgetingPengadaanEntity> getEntityClass() {
        return ItAkunBudgetingPengadaanEntity.class;
    }

    @Override
    public List<ItAkunBudgetingPengadaanEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunBudgetingPengadaanEntity.class);

        if (mapCriteria.get("id_pengadaan") != null){
            criteria.add(Restrictions.eq("idPengadaan", mapCriteria.get("id_pengadaan").toString()));
        }

        if (mapCriteria.get("id_budgeting_detail") != null){
            criteria.add(Restrictions.eq("idBudgetingDetail", mapCriteria.get("id_budgeting_detail").toString()));
        }

        if (mapCriteria.get("no_budgeting") != null){
            criteria.add(Restrictions.eq("noBudgeting", mapCriteria.get("no_budgeting").toString()));
        }

        if (mapCriteria.get("tipe") != null){
            criteria.add(Restrictions.eq("tipe", mapCriteria.get("tipe").toString()));
        }

        List<ItAkunBudgetingPengadaanEntity> results = criteria.list();
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_budgeting_pengadaan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
