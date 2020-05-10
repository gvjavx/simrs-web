package com.neurix.hris.transaksi.pendapatanDokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.pendapatanDokter.model.ImHrisPajakEntity;
import com.neurix.hris.transaksi.pendapatanDokter.model.PajakPendapatanDokter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class PajakPendapatanDokterDao extends GenericDao<ImHrisPajakEntity, String> {
    @Override
    protected Class<ImHrisPajakEntity> getEntityClass() {
        return ImHrisPajakEntity.class;
    }

    @Override
    public List<ImHrisPajakEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisPajakEntity.class);

        if (mapCriteria != null){
            if (mapCriteria.get("id_pajak") != null){
                criteria.add(Restrictions.eq("idPajak", (String) mapCriteria.get("id_pajak")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
//        criteria.add(Restrictions.eq("flag", "Y"));
        List<ImHrisPajakEntity> results = criteria.list();

        return results;
    }
}