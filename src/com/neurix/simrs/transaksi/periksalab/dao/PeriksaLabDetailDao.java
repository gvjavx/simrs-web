package com.neurix.simrs.transaksi.periksalab.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsPeriksaLabDetailEntity;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLabDetail;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PeriksaLabDetailDao extends GenericDao<ItSimrsPeriksaLabDetailEntity, String> {
    @Override
    protected Class<ItSimrsPeriksaLabDetailEntity> getEntityClass() {
        return ItSimrsPeriksaLabDetailEntity.class;
    }

    @Override
    public List<ItSimrsPeriksaLabDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPeriksaLabDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_periksa_lab_detail")!=null) {
                criteria.add(Restrictions.eq("idPeriksaLabDetail", (String) mapCriteria.get("id_periksa_lab_detail")));
            }
            if (mapCriteria.get("id_periksa_lab")!=null) {
                criteria.add(Restrictions.eq("idPeriksaLab", (String) mapCriteria.get("id_periksa_lab")));
            }
            if (mapCriteria.get("id_lab_detail")!=null) {
                criteria.add(Restrictions.eq("idLabDetail", (String) mapCriteria.get("id_lab_detail")));
            }
            if (mapCriteria.get("nama_detail_periksa")!=null) {
                criteria.add(Restrictions.ilike("namaDetailPeriksa", "%" + (String)mapCriteria.get("nama_detail_periksa") + "%"));
            }
            if (mapCriteria.get("satuan")!=null) {
                criteria.add(Restrictions.eq("satuan", (String) mapCriteria.get("satuan")));
            }
            if (mapCriteria.get("keterangan_acuan")!=null) {
                criteria.add(Restrictions.eq("keteranganAcuan", (String) mapCriteria.get("keterangan_acuan")));
            }
            if (mapCriteria.get("hasil")!=null) {
                criteria.add(Restrictions.eq("hasil", (String) mapCriteria.get("hasil")));
            }
            if (mapCriteria.get("keterangan_periksa")!=null) {
                criteria.add(Restrictions.eq("keteranganPeriksa", (String) mapCriteria.get("keterangan_periksa")));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idPeriksaLabDetail"));

        List<ItSimrsPeriksaLabDetailEntity> results = criteria.list();
        return results;
    }
}