package com.neurix.simrs.transaksi.diagnosarawat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class DiagnosaRawatDao extends GenericDao<ItSimrsDiagnosaRawatEntity, String> {

    @Override
    protected Class<ItSimrsDiagnosaRawatEntity> getEntityClass() {
        return ItSimrsDiagnosaRawatEntity.class;
    }

    @Override
    public List<ItSimrsDiagnosaRawatEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsDiagnosaRawatEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_diagnosa")!=null) {
                criteria.add(Restrictions.eq("idDiagnosa", (String) mapCriteria.get("id_diagnosa")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("jenis_diagnosa")!=null) {
                criteria.add(Restrictions.eq("jenisDiagnosa", (String) mapCriteria.get("jenis_diagnosa")));
            }
            if (mapCriteria.get("tipe")!=null) {
                criteria.add(Restrictions.eq("tipe", (String) mapCriteria.get("tipe")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idDiagnosa"));

        List<ItSimrsDiagnosaRawatEntity> results = criteria.list();
        return results;
    }
}