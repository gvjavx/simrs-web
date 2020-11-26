package com.neurix.simrs.transaksi.paketperiksa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsDetailPaketEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DetailPaketDao extends GenericDao<MtSimrsDetailPaketEntity, String> {

    @Override
    protected Class<MtSimrsDetailPaketEntity> getEntityClass() {
        return MtSimrsDetailPaketEntity.class;
    }

    @Override
    public List<MtSimrsDetailPaketEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsDetailPaketEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_detail_paket") != null) {
                criteria.add(Restrictions.eq("idDetailPaket", mapCriteria.get("id_detail_paket").toString()));
            }
            if (mapCriteria.get("id_paket") != null) {
                criteria.add(Restrictions.eq("idPaket", mapCriteria.get("id_paket").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
        }

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_paket')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
