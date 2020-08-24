package com.neurix.akuntansi.transaksi.budgetingperhitungan.dao;

import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ItAkunNilaiParameterBudgetingEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ItAkunPerhitunganBudgetingEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NilaiParameterDao extends GenericDao<ItAkunNilaiParameterBudgetingEntity, String>{

    @Override
    protected Class<ItAkunNilaiParameterBudgetingEntity> getEntityClass() {
        return ItAkunNilaiParameterBudgetingEntity.class;
    }

    @Override
    public List<ItAkunNilaiParameterBudgetingEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunNilaiParameterBudgetingEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("id_parameter") != null){
            criteria.add(Restrictions.eq("idParameter", mapCriteria.get("id_parameter").toString()));
        }
        if (mapCriteria.get("master_id") != null){
            criteria.add(Restrictions.eq("masterId", mapCriteria.get("master_id").toString()));
        }
        if (mapCriteria.get("divisi_id") != null){
            criteria.add(Restrictions.eq("divisiId", mapCriteria.get("divisi_id").toString()));
        }
        if (mapCriteria.get("branch_id") != null){
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        }
        if (mapCriteria.get("tahun") != null){
            criteria.add(Restrictions.eq("tahun", mapCriteria.get("tahun").toString()));
        }

//        criteria.addOrder(Order.asc("urutan"));
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_nilai_budgeting')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }
}
