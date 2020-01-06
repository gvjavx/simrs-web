package com.neurix.simrs.transaksi.permintaanvendor.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obatgejala.model.ImSimrsObatGejalaEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 06/01/20.
 */
public class TempObatGejalaDao extends GenericDao<ImSimrsObatGejalaEntity, String> {
    @Override
    protected Class<ImSimrsObatGejalaEntity> getEntityClass() {
        return ImSimrsObatGejalaEntity.class;
    }

    @Override
    public List<ImSimrsObatGejalaEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsObatGejalaEntity.class);

        if (criteria != null)
            if (mapCriteria.get("id_trans_obat_detail") != null)
                criteria.add(Restrictions.eq("idTransObatDetail", mapCriteria.get("id_trans_obat_detail").toString()));

        List<ImSimrsObatGejalaEntity> list = criteria.list();
        return list;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_temp_obat_gejala')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
