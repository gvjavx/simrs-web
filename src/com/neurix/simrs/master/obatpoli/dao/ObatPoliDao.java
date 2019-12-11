package com.neurix.simrs.master.obatpoli.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obatpoli.model.MtSimrsObatPoliEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 10/12/2019.
 */
public class ObatPoliDao extends GenericDao<MtSimrsObatPoliEntity,String> {

    @Override
    protected Class<MtSimrsObatPoliEntity> getEntityClass() {
        return MtSimrsObatPoliEntity.class;
    }

    @Override
    public List<MtSimrsObatPoliEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsObatPoliEntity.class);

        if (mapCriteria.get("id_obat") != null && mapCriteria.get("id_pelayanan") != null){
            criteria.add(Restrictions.eq("primaryKey.idObat", mapCriteria.get("id_obat")));
            criteria.add(Restrictions.eq("primaryKey.idPelayanan", mapCriteria.get("id_pelayanan")));
        }

        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }

        List<MtSimrsObatPoliEntity> results = criteria.list();
        return results;
    }
}
