package com.neurix.akuntansi.transaksi.budgetingperhitungan.dao;

import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ImAkunJenisBudgetingEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ItAkunNilaiBiayaRutinEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 14/08/20.
 */
public class BiayaRutinDao extends GenericDao<ItAkunNilaiBiayaRutinEntity, String>{

    @Override
    protected Class<ItAkunNilaiBiayaRutinEntity> getEntityClass() {
        return ItAkunNilaiBiayaRutinEntity.class;
    }

    @Override
    public List<ItAkunNilaiBiayaRutinEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunNilaiBiayaRutinEntity.class);

        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_nilai_biaya_rutin')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }
}
