package com.neurix.simrs.master.obatgejala.dao;

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
 * Created by Toshiba on 03/12/2019.
 */
public class ObatGejalaDao extends GenericDao<ImSimrsObatGejalaEntity, String> {


    @Override
    protected Class<ImSimrsObatGejalaEntity> getEntityClass() {
        return ImSimrsObatGejalaEntity.class;
    }

    @Override
    public List<ImSimrsObatGejalaEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsObatGejalaEntity.class);

        if (mapCriteria != null)
            if (mapCriteria.get("id_obat_gejala") != null)
                criteria.add(Restrictions.eq("idObatGejala", mapCriteria.get("id_obat_gejala").toString()));
            if (mapCriteria.get("id_obat") != null)
                criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat").toString()));
            if (mapCriteria.get("id_jenis_obat") != null)
                criteria.add(Restrictions.eq("idJenisObat", mapCriteria.get("id_jenis_obat").toString()));
            if (mapCriteria.get("flag") != null)
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));


        List<ImSimrsObatGejalaEntity> results = criteria.list();

        return results;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_obat_gejala')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
