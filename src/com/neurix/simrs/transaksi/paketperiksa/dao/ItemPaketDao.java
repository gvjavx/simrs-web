package com.neurix.simrs.transaksi.paketperiksa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsItemPaketEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 12/03/20.
 */
public class ItemPaketDao extends GenericDao<MtSimrsItemPaketEntity, String> {

    @Override
    protected Class<MtSimrsItemPaketEntity> getEntityClass() {
        return MtSimrsItemPaketEntity.class;
    }

    @Override
    public List<MtSimrsItemPaketEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsItemPaketEntity.class);

        if (mapCriteria.get("id_item_paket") != null){
            criteria.add(Restrictions.eq("idItemPaket", mapCriteria.get("id_item_paket").toString()));
        }
        if (mapCriteria.get("id_paket") != null){
            criteria.add(Restrictions.eq("idPaket", mapCriteria.get("id_paket").toString()));
        }
        if (mapCriteria.get("id_item") != null){
            criteria.add(Restrictions.eq("idItem", mapCriteria.get("id_item").toString()));
        }
        if (mapCriteria.get("id_kategori_item") != null){
            criteria.add(Restrictions.eq("idKategoriItem", mapCriteria.get("id_kategori_item").toString()));
        }
        if (mapCriteria.get("jenis_item") != null){
            criteria.add(Restrictions.eq("jenisItem", mapCriteria.get("jenis_item").toString()));
        }

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_item_paket')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
