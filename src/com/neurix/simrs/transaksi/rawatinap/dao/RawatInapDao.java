package com.neurix.simrs.transaksi.rawatinap.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class RawatInapDao extends GenericDao<ItSimrsRawatInapEntity, String> {
    @Override
    protected Class<ItSimrsRawatInapEntity> getEntityClass() {
        return ItSimrsRawatInapEntity.class;
    }

    @Override
    public List<ItSimrsRawatInapEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRawatInapEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_rawat_inap") != null)
                criteria.add(Restrictions.eq("idRawatInap", mapCriteria.get("id_rawat_inap").toString()));
            if (mapCriteria.get("id_detail_checkup") != null)
                criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
            if (mapCriteria.get("no_checkup") != null)
                criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
            if (mapCriteria.get("id_ruangan") != null)
                criteria.add(Restrictions.eq("idRuangan", mapCriteria.get("id_ruangan").toString()));

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        List<ItSimrsRawatInapEntity> result = criteria.list();
        return result;
    }
}
