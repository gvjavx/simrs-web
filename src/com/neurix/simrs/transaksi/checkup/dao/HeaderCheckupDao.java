package com.neurix.simrs.transaksi.checkup.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class HeaderCheckupDao extends GenericDao<ItSimrsHeaderChekupEntity, String> {
    @Override
    protected Class<ItSimrsHeaderChekupEntity> getEntityClass() {
        return ItSimrsHeaderChekupEntity.class;
    }

    @Override
    public List<ItSimrsHeaderChekupEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHeaderChekupEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("no_checkup") != null)
                criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
            if (mapCriteria.get("id_pasien") != null)
                criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
            if (mapCriteria.get("branch_id") != null)
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("id_branch").toString()));
            if (mapCriteria.get("desa_id") != null)
                criteria.add(Restrictions.eq("desaId", mapCriteria.get("desa_id").toString()));
            if (mapCriteria.get("no_ktp") != null)
                criteria.add(Restrictions.eq("noKtp", mapCriteria.get("no_ktp").toString()));

        List<ItSimrsHeaderChekupEntity> result = criteria.list();
        return result;
    }
}
