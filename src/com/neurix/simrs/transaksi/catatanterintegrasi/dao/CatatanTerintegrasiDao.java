package com.neurix.simrs.transaksi.catatanterintegrasi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.catatanterintegrasi.model.ItSimrsCatatanTerintegrasiEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CatatanTerintegrasiDao extends GenericDao<ItSimrsCatatanTerintegrasiEntity, String> {

    @Override
    protected Class<ItSimrsCatatanTerintegrasiEntity> getEntityClass() {
        return ItSimrsCatatanTerintegrasiEntity.class;
    }

    @Override
    public List<ItSimrsCatatanTerintegrasiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsCatatanTerintegrasiEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_catatan_terintegrasi")!=null) {
                criteria.add(Restrictions.eq("idCatatanTerintegrasi", (String) mapCriteria.get("id_catatan_terintegrasi")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.desc("idCatatanTerintegrasi"));

        List<ItSimrsCatatanTerintegrasiEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_catatan_terintegrasi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}