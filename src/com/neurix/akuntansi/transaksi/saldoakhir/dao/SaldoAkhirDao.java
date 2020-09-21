package com.neurix.akuntansi.transaksi.saldoakhir.dao;

import com.neurix.akuntansi.transaksi.saldoakhir.model.ItAkunSaldoAkhirEntity;
import com.neurix.akuntansi.transaksi.saldoakhir.model.SaldoAkhir;
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
public class SaldoAkhirDao extends GenericDao<ItAkunSaldoAkhirEntity, String> {

    @Override
    protected Class<ItAkunSaldoAkhirEntity> getEntityClass() {
        return ItAkunSaldoAkhirEntity.class;
    }

    @Override
    public List<ItAkunSaldoAkhirEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunSaldoAkhirEntity.class);
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
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_saldo_akhir')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }

    public SaldoAkhir getLastSaldoAkhirByTahun(String tahun, String branchId) {

        String SQL = "SELECT a.periode, a.branch_id FROM it_akun_saldo_akhir a \n" +
                "WHERE a.periode ILIKE :tahun \n" +
                "AND a.branch_id = :branch \n" +
                "ORDER BY a.created_date LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("tahun", "%"+tahun)
                .setParameter("branch", branchId)
                .list();

        if (results.size() > 0){
            for (Object[] obj : results){
                SaldoAkhir saldoAkhir = new SaldoAkhir();
                saldoAkhir.setPeriode(obj[0].toString());
                saldoAkhir.setBranchId(obj[1].toString());
                return saldoAkhir;
            }
        }

        return null;
    }
}
