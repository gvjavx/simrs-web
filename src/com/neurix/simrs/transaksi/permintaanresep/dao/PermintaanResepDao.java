package com.neurix.simrs.transaksi.permintaanresep.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 11/12/2019.
 */
public class PermintaanResepDao extends GenericDao<ImSimrsPermintaanResepEntity, String> {
    @Override
    protected Class<ImSimrsPermintaanResepEntity> getEntityClass() {
        return ImSimrsPermintaanResepEntity.class;
    }

    @Override
    public List<ImSimrsPermintaanResepEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPermintaanResepEntity.class);
        if (mapCriteria.get("id_permintaan_resep") != null){
            criteria.add(Restrictions.eq("idPermintaanResep", mapCriteria.get("id_permintaan_resep").toString()));
        }
        if (mapCriteria.get("id_approval_obat") != null){
            criteria.add(Restrictions.eq("idApprovalObat", mapCriteria.get("id_approval_obat").toString()));
        }
        if (mapCriteria.get("id_pasien") != null){
            criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
        }
        if (mapCriteria.get("id_detail_checkup") != null){
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        }
        if (mapCriteria.get("branch_id") != null){
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        List<ImSimrsPermintaanResepEntity> results = criteria.list();
        return results;
    }



    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_permintaan_resep')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
