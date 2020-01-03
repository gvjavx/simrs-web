package com.neurix.simrs.transaksi.permintaanvendor.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.permintaanvendor.model.MtSimrsPermintaanVendorEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 27/12/2019.
 */
public class PermintaanVendorDao extends GenericDao<MtSimrsPermintaanVendorEntity, String> {

    @Override
    protected Class<MtSimrsPermintaanVendorEntity> getEntityClass() {
        return MtSimrsPermintaanVendorEntity.class;
    }

    @Override
    public List<MtSimrsPermintaanVendorEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsPermintaanVendorEntity.class);
        if (mapCriteria.get("id_permintaan_vendor") != null)
            criteria.add(Restrictions.eq("idPermintaanVendor", mapCriteria.get("id_permintaan_vendor").toString()));
        if (mapCriteria.get("id_approval_obat") != null)
            criteria.add(Restrictions.eq("idApprovalObat", mapCriteria.get("id_approval_obat").toString()));
        if (mapCriteria.get("branch_id") != null)
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        List<MtSimrsPermintaanVendorEntity> list = criteria.list();
        return list;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_permintaan_vendor')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
