package com.neurix.simrs.transaksi.asesmenrawatjalan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmenrawatjalan.model.ItSimrsAsesmenKeperawatanRawatJalanEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class KeperawatanRawatJalanDao extends GenericDao<ItSimrsAsesmenKeperawatanRawatJalanEntity, String> {

    @Override
    protected Class<ItSimrsAsesmenKeperawatanRawatJalanEntity> getEntityClass() {
        return ItSimrsAsesmenKeperawatanRawatJalanEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenKeperawatanRawatJalanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenKeperawatanRawatJalanEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_keperawatan_rawat_jalan")!=null) {
                criteria.add(Restrictions.eq("idKeperawatanRawatJalan", (String) mapCriteria.get("id_keperawatan_rawat_jalan")));
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
        criteria.addOrder(Order.asc("idKeperawatanRawatJalan"));

        List<ItSimrsAsesmenKeperawatanRawatJalanEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_keperawatan_rawat_jalan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}