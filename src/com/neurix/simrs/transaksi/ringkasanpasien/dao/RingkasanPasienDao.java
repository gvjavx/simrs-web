package com.neurix.simrs.transaksi.ringkasanpasien.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.ringkasanpasien.model.ItSimrsRingkasanPasienEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RingkasanPasienDao extends GenericDao<ItSimrsRingkasanPasienEntity, String> {

    @Override
    protected Class<ItSimrsRingkasanPasienEntity> getEntityClass() {
        return ItSimrsRingkasanPasienEntity.class;
    }

    @Override
    public List<ItSimrsRingkasanPasienEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRingkasanPasienEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_ringkasan_pasien")!=null) {
                criteria.add(Restrictions.eq("idRingkasanPasien", (String) mapCriteria.get("id_ringkasan_pasien")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("no_checkup")!=null) {
                criteria.add(Restrictions.eq("noCheckup", (String) mapCriteria.get("no_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if (mapCriteria.get("jenis")!=null) {
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idRingkasanPasien"));

        List<ItSimrsRingkasanPasienEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_ringkasan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}