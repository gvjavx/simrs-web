package com.neurix.akuntansi.transaksi.saldoakhir.dao;

import com.neurix.akuntansi.transaksi.saldoakhir.model.ItAkunSaldoAkhirTahunEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 20/03/20.
 */
public class SaldoAkhirTahunDao extends GenericDao<ItAkunSaldoAkhirTahunEntity, String> {

    @Override
    protected Class<ItAkunSaldoAkhirTahunEntity> getEntityClass() {
        return ItAkunSaldoAkhirTahunEntity.class;
    }

    @Override
    public List<ItAkunSaldoAkhirTahunEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunSaldoAkhirTahunEntity.class);
        if (mapCriteria.get("saldo_akhir_id") != null){
            criteria.add(Restrictions.eq("saldoAkhirId", mapCriteria.get("saldo_akhir_id").toString()));
        }
        if (mapCriteria.get("rekening_id") != null){
            criteria.add(Restrictions.eq("rekeningId", mapCriteria.get("rekening_id").toString()));
        }
        if (mapCriteria.get("periode") != null){
            criteria.add(Restrictions.eq("periode", mapCriteria.get("periode").toString()));
        }
        if (mapCriteria.get("branch_id") != null){
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        }
        if (mapCriteria.get("rekening_id") != null){
            criteria.add(Restrictions.eq("rekeningId", mapCriteria.get("rekening_id").toString()));
        }
        if (mapCriteria.get("master_id") != null){
            criteria.add(Restrictions.eq("masterId", mapCriteria.get("master_id").toString()));
        }
        if (mapCriteria.get("pasien_id") != null){
            criteria.add(Restrictions.eq("pasienId", mapCriteria.get("pasien_id").toString()));
        }

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_saldo_akhir_tahun')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }
}
