package com.neurix.simrs.transaksi.transaksiobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsApprovalTransaksiObatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApprovalTransaksiObatDao extends GenericDao<ImtSimrsApprovalTransaksiObatEntity, String> {
    @Override
    protected Class<ImtSimrsApprovalTransaksiObatEntity> getEntityClass() {
        return ImtSimrsApprovalTransaksiObatEntity.class;
    }

    @Override
    public List<ImtSimrsApprovalTransaksiObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImtSimrsApprovalTransaksiObatEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_approval_obat")!=null) {
                criteria.add(Restrictions.eq("idApprovalObat", (String) mapCriteria.get("id_approval_obat")));
            }
            if (mapCriteria.get("id_obat")!=null) {
                criteria.add(Restrictions.eq("idObat", (String)mapCriteria.get("id_obat")));
            }
            if (mapCriteria.get("tipe_permintaan")!=null) {
                criteria.add(Restrictions.eq("tipePermintaan", (String) mapCriteria.get("tipe_permintaan")));
            }
            if (mapCriteria.get("approval_flag")!=null) {
                criteria.add(Restrictions.eq("approvalFlag", (String) mapCriteria.get("approval_flag")));
            }
            if (mapCriteria.get("approval_person")!=null) {
                criteria.add(Restrictions.eq("approvePerson", (String) mapCriteria.get("approval_person")));
            }
            if (mapCriteria.get("qty")!=null) {
                criteria.add(Restrictions.eq("qty", mapCriteria.get("qty")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idApprovalObat"));

        List<ImtSimrsApprovalTransaksiObatEntity> results = criteria.list();

        return results;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_approval_obat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}