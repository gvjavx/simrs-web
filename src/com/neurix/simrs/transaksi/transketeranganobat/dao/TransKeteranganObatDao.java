package com.neurix.simrs.transaksi.transketeranganobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.keteranganobat.model.ImSimrsKeteranganObatEntity;
import com.neurix.simrs.transaksi.transketeranganobat.model.ItSimrsKeteranganResepEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TransKeteranganObatDao extends GenericDao<ItSimrsKeteranganResepEntity, String>{

    @Override
    protected Class<ItSimrsKeteranganResepEntity> getEntityClass() {
        return ItSimrsKeteranganResepEntity.class;
    }

    @Override
    public List<ItSimrsKeteranganResepEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKeteranganObatEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_permintaan_resep") != null)
            criteria.add(Restrictions.eq("idPermintaanResep", mapCriteria.get("id_permintaan_resep").toString()));
        if (mapCriteria.get("id_obat") != null)
            criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat").toString()));
        if (mapCriteria.get("id_keterangan_obat") != null)
            criteria.add(Restrictions.eq("idKeteranganObat", mapCriteria.get("id_keterangan_obat").toString()));

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_trans_keterangan_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%07d", iter.next());
        return "TKO" + sId;
    }
}
