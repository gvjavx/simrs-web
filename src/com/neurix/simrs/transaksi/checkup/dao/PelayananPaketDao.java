package com.neurix.simrs.transaksi.checkup.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsPelayananPaketEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PelayananPaketDao extends GenericDao<ItSimrsPelayananPaketEntity, String> {
    @Override
    protected Class<ItSimrsPelayananPaketEntity> getEntityClass() {
        return ItSimrsPelayananPaketEntity.class;
    }

    @Override
    public List<ItSimrsPelayananPaketEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPelayananPaketEntity.class);
        if (mapCriteria.get("id_pelayanan_paket") != null){
            criteria.add(Restrictions.eq("idPelayananPaket", mapCriteria.get("id_pelayanan_paket").toString()));
        }
        if (mapCriteria.get("id_paket") != null){
            criteria.add(Restrictions.eq("idPaket", mapCriteria.get("id_paket").toString()));
        }
        if (mapCriteria.get("id_detail_checkup") != null){
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        }
        if(mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }
        List<ItSimrsPelayananPaketEntity> list = criteria.list();
        return list;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pelayanan_paket')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
