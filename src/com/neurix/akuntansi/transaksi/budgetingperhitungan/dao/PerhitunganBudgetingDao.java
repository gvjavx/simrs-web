package com.neurix.akuntansi.transaksi.budgetingperhitungan.dao;

import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ItAkunPerhitunganBudgetingEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.PerhitunganBudgeting;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 14/08/20.
 */
public class PerhitunganBudgetingDao extends GenericDao<ItAkunPerhitunganBudgetingEntity, String> {
    @Override
    protected Class<ItAkunPerhitunganBudgetingEntity> getEntityClass() {
        return ItAkunPerhitunganBudgetingEntity.class;
    }

    @Override
    public List<ItAkunPerhitunganBudgetingEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPerhitunganBudgetingEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("id_parameter_budgeting") != null){
            criteria.add(Restrictions.eq("idParameterBudgeting", mapCriteria.get("id_parameter_budgeting").toString()));
        }
        if (mapCriteria.get("branch_id") != null){
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        }
        if (mapCriteria.get("tahun") != null){
            criteria.add(Restrictions.eq("tahun", mapCriteria.get("tahun").toString()));
        }

        criteria.addOrder(Order.asc("urutan"));
        return criteria.list();
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_perhitungan_budgeting')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<PerhitunganBudgeting> getListPerhitunganPendapatanTindakan(String branchId, String tahun, String bulan){

        String SQL = "SELECT \n" +
                "a.*,\n" +
                "a.standard_cost * a.qty as total,\n" +
                "a.harga_diskon * a.qty as total_diskon\n" +
                "FROM (\n" +
                "\tSELECT \n" +
                "\tht.nama_tindakan,\n" +
                "\tht.standard_cost,\n" +
                "\tht.harga_diskon, \n" +
                "\tCOUNT (td.id_tindakan_rawat) as qty\n" +
                "\tFROM im_simrs_header_tindakan ht\n" +
                "\tINNER JOIN im_simrs_tindakan mt ON mt.id_header_tindakan = ht.id\n" +
                "\tINNER JOIN it_simrs_tindakan_rawat td ON td.id_tindakan = mt.id_tindakan\n" +
                "\tINNER JOIN it_simrs_header_detail_checkup dc ON dc.id_detail_checkup = td.id_detail_checkup\n" +
                "\tINNER JOIN it_akun_jurnal jd ON jd.no_jurnal = dc.no_jurnal\n" +
                "\tWHERE qty > 0\n" +
                "\tAND td.approve_flag = 'Y'\n" +
                "\tAND dc.branch_id = :unit \n" +
                "\tAND EXTRACT(YEAR from jd.tanggal_jurnal) = :tahun \n" +
                "\tAND EXTRACT(MONTH from jd.tanggal_jurnal) <= :bulan \n" +
                "\tGROUP BY \n" +
                "\tht.nama_tindakan,\n" +
                "\tht.standard_cost,\n" +
                "\tht.harga_diskon\n" +
                "\tORDER BY qty DESC\n" +
                ")a\n";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", branchId)
                .setParameter("tahun", tahun)
                .setParameter("bulan", bulan)
                .list();

        List<PerhitunganBudgeting> perhitunganBudgetings = new ArrayList<>();
        if (results.size() > 0){
            PerhitunganBudgeting perhitunganBudgeting;
            for (Object[] obj : results){
                perhitunganBudgeting = new PerhitunganBudgeting();
                perhitunganBudgeting.setNamaTindakan(obj[0].toString());
                perhitunganBudgeting.setHarga(obj[1] == null ? new BigDecimal(0) : (BigDecimal) obj[1]);
                perhitunganBudgeting.setHargaDiskon(obj[2] == null ? new BigDecimal(0) : (BigDecimal) obj[2]);
                perhitunganBudgeting.setQty(obj[3] == null ? new BigDecimal(0) : (BigDecimal) obj[3]);
                perhitunganBudgeting.setTotalHarga(obj[4] == null ? new BigDecimal(4) : (BigDecimal) obj[4]);
                perhitunganBudgeting.setTotalHargaDiskon(obj[5] == null ? new BigDecimal(0) : (BigDecimal) obj[5]);
                perhitunganBudgetings.add(perhitunganBudgeting);
            }
        }
        return perhitunganBudgetings;
    }

}
