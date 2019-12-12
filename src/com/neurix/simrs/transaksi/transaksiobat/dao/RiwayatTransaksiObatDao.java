package com.neurix.simrs.transaksi.transaksiobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsRiwayatTransaksiObatEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class RiwayatTransaksiObatDao extends GenericDao<ImtSimrsRiwayatTransaksiObatEntity, String> {
    @Override
    protected Class<ImtSimrsRiwayatTransaksiObatEntity> getEntityClass() {
        return ImtSimrsRiwayatTransaksiObatEntity.class;
    }

    @Override
    public List<ImtSimrsRiwayatTransaksiObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImtSimrsRiwayatTransaksiObatEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_riwayat_transaksi_obat")!=null) {
                criteria.add(Restrictions.eq("idRiwayatTransaksiObat", (String) mapCriteria.get("id_riwayat_transaksi_obat")));
            }
            if (mapCriteria.get("id_approval_obat")!=null) {
                criteria.add(Restrictions.ilike("idApprovalObat", (String)mapCriteria.get("id_approval_obat")));
            }
            if (mapCriteria.get("id_obat")!=null) {
                criteria.add(Restrictions.eq("idObat", (String) mapCriteria.get("id_obat")));
            }
            if (mapCriteria.get("tipe_permintaan")!=null) {
                criteria.add(Restrictions.eq("tipePermintaan", (String) mapCriteria.get("tipe_permintaan")));
            }
            if (mapCriteria.get("qty")!=null) {
                criteria.add(Restrictions.eq("qty", mapCriteria.get("qty")));
            }
            if (mapCriteria.get("total_harga")!=null) {
                criteria.add(Restrictions.eq("totalHarga", mapCriteria.get("total_harga")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idRiwayatTransaksiObat"));

        List<ImtSimrsRiwayatTransaksiObatEntity> results = criteria.list();

        return results;
    }
}