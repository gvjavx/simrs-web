package com.neurix.simrs.transaksi.edukasipasien.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.edukasipasien.model.ItSimrsEdukasiPasienEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EdukasiPasienDao extends GenericDao<ItSimrsEdukasiPasienEntity, String> {

    @Override
    protected Class<ItSimrsEdukasiPasienEntity> getEntityClass() {
        return ItSimrsEdukasiPasienEntity.class;
    }

    @Override
    public List<ItSimrsEdukasiPasienEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsEdukasiPasienEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_edukasi_pasien")!=null) {
                criteria.add(Restrictions.eq("idEdukasiPasien", (String) mapCriteria.get("id_edukasi_pasien")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idEdukasiPasien"));

        List<ItSimrsEdukasiPasienEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_edukasi_pasien')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}