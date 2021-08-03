package com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.dao;

import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model.ItAkunPendaftaranJasaEntity;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.*;

public class PendaftaranJasaRekananDao extends GenericDao<ItAkunPendaftaranJasaEntity, String>{

    @Override
    protected Class<ItAkunPendaftaranJasaEntity> getEntityClass() {
        return ItAkunPendaftaranJasaEntity.class;
    }

    @Override
    public List<ItAkunPendaftaranJasaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPendaftaranJasaEntity.class);

        List<ItAkunPendaftaranJasaEntity> result = new ArrayList<>();
        if (mapCriteria != null) {
            if (mapCriteria.get("id") != null){
                criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
            }
            if(mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId",  mapCriteria.get("branch_id").toString()));
            }
            if (mapCriteria.get("approve_ka_keu") != null){
                criteria.add(Restrictions.eq("approveKaKeu", mapCriteria.get("approve_ka_keu")));
            }
            if (mapCriteria.get("approve_kasub_keu") != null){
                criteria.add(Restrictions.eq("approveKasubKeu", mapCriteria.get("approve_kasub_keu")));
            }
            if (mapCriteria.get("approve_keu") != null){
                criteria.add(Restrictions.eq("approveKeu", mapCriteria.get("approve_keu")));
            }
            if (mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
            result = criteria.list();
        }

        return result;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('pendaftaran_jasa_rekanan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "P"+ CommonUtil.stDateSeq() + sId;
    }
}
