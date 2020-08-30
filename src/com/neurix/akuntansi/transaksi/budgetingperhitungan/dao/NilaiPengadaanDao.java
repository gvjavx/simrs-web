package com.neurix.akuntansi.transaksi.budgetingperhitungan.dao;

import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ItAkunNilaiParameterPengadaaanEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ItAkunPerhitunganBudgetingEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NilaiPengadaanDao extends GenericDao<ItAkunNilaiParameterPengadaaanEntity, String>{
    @Override
    protected Class<ItAkunNilaiParameterPengadaaanEntity> getEntityClass() {
        return ItAkunNilaiParameterPengadaaanEntity.class;
    }

    @Override
    public List<ItAkunNilaiParameterPengadaaanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunNilaiParameterPengadaaanEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("id_nilai_param") != null){
            criteria.add(Restrictions.eq("idNilaiParameter", mapCriteria.get("id_nilai_param").toString()));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        return criteria.list();
    }


    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_nilai_pengadaan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

}
