package com.neurix.simrs.transaksi.periksalab.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsPeriksaLabEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PeriksaLabDao extends GenericDao<ItSimrsPeriksaLabEntity, String> {
    @Override
    protected Class<ItSimrsPeriksaLabEntity> getEntityClass() {
        return ItSimrsPeriksaLabEntity.class;
    }

    @Override
    public List<ItSimrsPeriksaLabEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPeriksaLabEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_periksa_lab")!=null) {
                criteria.add(Restrictions.eq("idPeriksaLab", (String) mapCriteria.get("id_periksa_lab")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("tanggal_masuk_lab")!=null) {
                criteria.add(Restrictions.eq("tanggalMasukLab", (Date) mapCriteria.get("tanggal_masuk_lab")));
            }
            if (mapCriteria.get("tanggal_selesai_periksa")!=null) {
                criteria.add(Restrictions.eq("tanggalSelesaiPeriksa", (Timestamp) mapCriteria.get("tanggal_selesai_periksa")));
            }
            if (mapCriteria.get("id_dokter_pengirim")!=null) {
                criteria.add(Restrictions.eq("idDokterPengirim", (String) mapCriteria.get("id_dokter_pengirim")));
            }
            if (mapCriteria.get("id_dokter")!=null) {
                criteria.add(Restrictions.eq("idDokter", (String) mapCriteria.get("id_dokter")));
            }
            if (mapCriteria.get("id_pemeriksa")!=null) {
                criteria.add(Restrictions.eq("idPemeriksa", (String) mapCriteria.get("id_pemeriksa")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idPeriksaLab"));

        List<ItSimrsPeriksaLabEntity> results = criteria.list();
        return results;
    }
}