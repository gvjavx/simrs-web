package com.neurix.akuntansi.transaksi.tutupperiod.dao;

import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 18/03/20.
 */
public class BatasTutupPeriodDao extends GenericDao<ItSimrsBatasTutupPeriodEntity, String> {

    @Override
    protected Class<ItSimrsBatasTutupPeriodEntity> getEntityClass() {
        return ItSimrsBatasTutupPeriodEntity.class;
    }

    @Override
    public List<ItSimrsBatasTutupPeriodEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsBatasTutupPeriodEntity.class);
        if (mapCriteria.get("tahun") != null)
            criteria.add(Restrictions.eq("tahun", mapCriteria.get("tahun").toString()));
        if (mapCriteria.get("bulan") != null)
            criteria.add(Restrictions.eq("bulan", mapCriteria.get("bulan").toString()));
        if (mapCriteria.get("unit") != null)
            criteria.add(Restrictions.eq("unit", mapCriteria.get("unit").toString()));

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_batas_tutup_period')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
