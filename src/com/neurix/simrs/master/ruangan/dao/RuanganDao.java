package com.neurix.simrs.master.ruangan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class RuanganDao extends GenericDao<MtSimrsRuanganEntity, String> {
    @Override
    protected Class<MtSimrsRuanganEntity> getEntityClass() {
        return MtSimrsRuanganEntity.class;
    }

    @Override
    public List<MtSimrsRuanganEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(MtSimrsRuanganEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_ruangan")!=null) {
                criteria.add(Restrictions.eq("idRuangan", (String) mapCriteria.get("id_ruangan")));
            }
            if (mapCriteria.get("nama_rangan")!=null) {
                criteria.add(Restrictions.ilike("namaRuangan", "%" + (String)mapCriteria.get("nama_rangan") + "%"));
            }
            if (mapCriteria.get("no_ruangan")!=null) {
                criteria.add(Restrictions.eq("noRuangan", (String) mapCriteria.get("no_ruangan")));
            }
            if (mapCriteria.get("status_ruangan")!=null) {
                criteria.add(Restrictions.eq("statusRuangan", (String) mapCriteria.get("status_ruangan")));
            }
            if (mapCriteria.get("id_kelas_ruangan")!=null) {
                criteria.add(Restrictions.eq("idKelasRuangan", (String) mapCriteria.get("id_kelas_ruangan")));
            }
            if (mapCriteria.get("tarif")!=null) {
                criteria.add(Restrictions.eq("tarif", (BigInteger) mapCriteria.get("tarif")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (BigInteger) mapCriteria.get("branch_id")));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idRuangan"));

        List<MtSimrsRuanganEntity> results = criteria.list();

        return results;
    }
}