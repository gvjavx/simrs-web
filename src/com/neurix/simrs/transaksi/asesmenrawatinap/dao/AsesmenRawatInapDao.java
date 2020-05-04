package com.neurix.simrs.transaksi.asesmenrawatinap.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.ItSimrsAsesmenRawatInapEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AsesmenRawatInapDao extends GenericDao<ItSimrsAsesmenRawatInapEntity, String> {

    @Override
    protected Class<ItSimrsAsesmenRawatInapEntity> getEntityClass() {
        return ItSimrsAsesmenRawatInapEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenRawatInapEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenRawatInapEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_asesmen_keperawatan_rawat_inap")!=null) {
                criteria.add(Restrictions.eq("idAsesmenKeperawatanRawatInap", (String) mapCriteria.get("id_asesmen_keperawatan_rawat_inap")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if (mapCriteria.get("jenis")!=null) {
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idAsesmenKeperawatanRawatInap"));

        List<ItSimrsAsesmenRawatInapEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asesmen_rawat_inap')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}