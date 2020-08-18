package com.neurix.simrs.transaksi.verifikatorasuransi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.verifikatorasuransi.model.ItSimrsStrukAsuransiEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 17/07/20.
 */
public class StrukAsuransiDao extends GenericDao<ItSimrsStrukAsuransiEntity, String> {

    @Override
    protected Class<ItSimrsStrukAsuransiEntity> getEntityClass() {
        return ItSimrsStrukAsuransiEntity.class;
    }

    @Override
    public List<ItSimrsStrukAsuransiEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsStrukAsuransiEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_detail_checkup") != null)
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        if (mapCriteria.get("approve_flag") != null)
            criteria.add(Restrictions.eq("approveFlag", mapCriteria.get("approve_flag").toString()));
        if (mapCriteria.get("approve_flag_null") != null)
            criteria.add(Restrictions.isNull("approveFlag"));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        if (mapCriteria.get("branch_id") != null)
            criteria.add(Restrictions.eq("branhcId", mapCriteria.get("branch_id").toString()));
        if (mapCriteria.get("id_antrian_telemedic") != null)
            criteria.add(Restrictions.eq("idAntrianTelemedic", mapCriteria.get("id_antrian_telemedic").toString()));
        if (mapCriteria.get("jenis") != null)
            criteria.add(Restrictions.eq("jenis", mapCriteria.get("jenis").toString()));

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_struk_asuransi')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
