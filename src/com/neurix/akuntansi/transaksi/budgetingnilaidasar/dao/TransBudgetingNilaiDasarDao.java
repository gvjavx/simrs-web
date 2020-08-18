package com.neurix.akuntansi.transaksi.budgetingnilaidasar.dao;

import com.neurix.akuntansi.transaksi.budgeting.model.BudgetingDetail;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.BudgetingNilaiDasar;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.ItAkunBudgetingNilaiDasarEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 07/08/20.
 */
public class TransBudgetingNilaiDasarDao extends GenericDao<ItAkunBudgetingNilaiDasarEntity, String>{

    @Override
    protected Class<ItAkunBudgetingNilaiDasarEntity> getEntityClass() {
        return ItAkunBudgetingNilaiDasarEntity.class;
    }

    @Override
    public List<ItAkunBudgetingNilaiDasarEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunBudgetingNilaiDasarEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("tahun") != null)
            criteria.add(Restrictions.eq("tahun", mapCriteria.get("tahun").toString()));
        if (mapCriteria.get("branch_id") != null)
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        if (mapCriteria.get("id_nilai_dasar") != null)
            criteria.add(Restrictions.eq("idNilaiDasar", mapCriteria.get("id_nilai_dasar").toString()));

        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    public List<BudgetingNilaiDasar> getBudgetingTahun(String tahun){

        if (tahun == null || "".equalsIgnoreCase(tahun))
            tahun = "%";

        String SQL = "SELECT tahun FROM it_akun_budgeting_nilai_dasar \n" +
                "WHERE tahun LIKE :tahun \n" +
                "GROUP BY tahun;";

        List<Object> resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("tahun", tahun)
                .list();

        List<BudgetingNilaiDasar> budgetingNilaiDasars = new ArrayList<>();
        BudgetingNilaiDasar budgetingNilaiDasar;
        for (Object obj : resuts){
            budgetingNilaiDasar = new BudgetingNilaiDasar();
            budgetingNilaiDasar.setTahun(obj.toString());
            budgetingNilaiDasar.setKeterangan("Nilai Dasar Budgeting Tahun "+obj.toString());
            budgetingNilaiDasars.add(budgetingNilaiDasar);
        }

        return budgetingNilaiDasars;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_trans_budgeting_nilai_dasar')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
