package com.neurix.akuntansi.master.parameterbudgeting.dao;

import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunKategoriParameterBudgetingEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class KategoriParameterBudgetingDao extends GenericDao<ImAkunKategoriParameterBudgetingEntity, String>{
    @Override
    protected Class<ImAkunKategoriParameterBudgetingEntity> getEntityClass() {
        return ImAkunKategoriParameterBudgetingEntity.class;
    }

    @Override
    public List<ImAkunKategoriParameterBudgetingEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImAkunKategoriParameterBudgetingEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("nama") != null)
            criteria.add(Restrictions.ilike("nama", "%" + mapCriteria.get("nama").toString() + "%"));

        if (mapCriteria.get("id_jenis_budgeting") != null){
            criteria.add(Restrictions.eq("idJenisBudgeting", mapCriteria.get("id_jenis_budgeting").toString()));
        }
        if (mapCriteria.get("flag") != null) {
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kategori_parameter_budgeting')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "KTB"+sId;
    }

    public List<ImAkunKategoriParameterBudgetingEntity> getkateoriparameterbudgeting(String nama ) throws HibernateException {
        List<ImAkunKategoriParameterBudgetingEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImAkunKategoriParameterBudgetingEntity.class)
                .add(Restrictions.ilike("nama", nama))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)
        return results;
    }
}
