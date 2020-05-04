package com.neurix.akuntansi.transaksi.budgeting.dao;

import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.budgeting.model.ItAkunBudgetingEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 29/04/20.
 */
public class BudgetingDao extends GenericDao<ItAkunBudgetingEntity, String> {

    @Override
    protected Class<ItAkunBudgetingEntity> getEntityClass() {
        return ItAkunBudgetingEntity.class;
    }

    @Override
    public List<ItAkunBudgetingEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunBudgetingEntity.class);

        if (mapCriteria.get("id_budgeting") != null){
            criteria.add(Restrictions.eq("idBudgeting", mapCriteria.get("id_budgeting").toString()));
        }
        if (mapCriteria.get("no_budgeting") != null){
            criteria.add(Restrictions.eq("noBudgeting", mapCriteria.get("no_budgeting").toString()));
        }
        if (mapCriteria.get("tahun") != null){
            criteria.add(Restrictions.eq("tahun", mapCriteria.get("tahun").toString()));
        }
        if (mapCriteria.get("branch_id") != null){
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        }
        if (mapCriteria.get("approve_flag") != null){
            criteria.add(Restrictions.eq("approveFlag", mapCriteria.get("approve_flag").toString()));
        }
        if (mapCriteria.get("tipe") != null){
            criteria.add(Restrictions.eq("tipe", mapCriteria.get("tipe").toString()));
        }

        if (mapCriteria.get("status") != null){
            criteria.add(Restrictions.eq("status", mapCriteria.get("status").toString()));
        }


        List<ItAkunBudgetingEntity> akunBudgetingEntities = criteria.list();
        return akunBudgetingEntities;
    }

    public List<ImKodeRekeningEntity> getCoaLastLevel(String id){
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImKodeRekeningEntity.class);
        criteria.add(Restrictions.eq("level", new Long(5)));
        criteria.add(Restrictions.ilike("kodeRekening", id));
        return criteria.list();
    }

    public BigDecimal getSumNilaiByStatus(String rekeningId, String branchId, String tahun, String status){
        String SQL = "SELECT rekening_id, SUM(nilai_total) \n" +
                "FROM it_akun_budgeting\n" +
                "WHERE rekening_id = :rekening \n" +
                "AND branch_id = :unit \n" +
                "AND tahun = :tahun \n" +
                "AND status LIKE :status \n" +
                "GROUP BY rekening_id";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("rekening", rekeningId)
                .setParameter("unit", branchId)
                .setParameter("tahun", tahun)
                .setParameter("status", "%"+status)
                .list();

        if (results.size()>0){
            for (Object[] obj : results){
                return obj[1] == null ? new BigDecimal(0) : (BigDecimal) obj[1];
            }
        }

        return new BigDecimal(0);
    }

    public Boolean checkIfSameStatus(String branchId, String tahun, String status){

        String SQL = "SELECT no_budgeting, status\n" +
                "FROM it_akun_budgeting \n" +
                "WHERE tahun = :tahun\n" +
                "AND branch_id = :branch\n" +
                "AND status ILIKE :status\n" +
                "LIMIT 1";

        List<Object[]> resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("tahun", tahun)
                .setParameter("branch", branchId)
                .setParameter("status", "%" + status)
                .list();

        if (resuts.size() > 0){
            return true;
        }
        return false;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_budgeting')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
