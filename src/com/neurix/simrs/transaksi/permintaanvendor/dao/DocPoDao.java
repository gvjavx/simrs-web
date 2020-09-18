package com.neurix.simrs.transaksi.permintaanvendor.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.permintaanvendor.model.ImUserVendorEntity;
import com.neurix.simrs.transaksi.permintaanvendor.model.ItSimrsDocPoEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 03/09/20.
 */
public class DocPoDao extends GenericDao<ItSimrsDocPoEntity, String> {
    @Override
    protected Class<ItSimrsDocPoEntity> getEntityClass() {
        return ItSimrsDocPoEntity.class;
    }

    @Override
    public List<ItSimrsDocPoEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsDocPoEntity.class);
        if (mapCriteria.get("id") != null) {
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("id_item") != null) {
            criteria.add(Restrictions.eq("idItem", mapCriteria.get("id_item").toString()));
        }
        if (mapCriteria.get("id_permintaan_vendor") != null) {
            criteria.add(Restrictions.eq("idPermintaanObatVendor", mapCriteria.get("id_permintaan_vendor").toString()));
        }
        if (mapCriteria.get("jenis_nomor") != null) {
            criteria.add(Restrictions.eq("jenisNomor", mapCriteria.get("jenis_nomor").toString()));
        }
        if (mapCriteria.get("tipe") != null) {
            criteria.add(Restrictions.eq("tipe", mapCriteria.get("tipe").toString()));
        }
        if (mapCriteria.get("flag") != null) {
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_doc_po')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
