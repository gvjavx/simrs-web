package com.neurix.akuntansi.transaksi.budgeting.dao;

import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import com.neurix.akuntansi.transaksi.budgeting.model.ItAkunBudgetingEntity;
import com.neurix.akuntansi.transaksi.saldoakhir.model.SaldoAkhir;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
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

        if (mapCriteria.get("rekening_id_list") != null){
            criteria.add(Restrictions.in("rekeningId", (List<String>) mapCriteria.get("rekening_id_list")));
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

    public BigDecimal getSumNilaiByStatus(String rekeningId, String branchId, String tahun, String status, String approveFlag){

        if (approveFlag != null && !"".equalsIgnoreCase(approveFlag)){
            approveFlag = "AND approve_flag = '"+approveFlag+"' \n";
        }

        String SQL = "SELECT rekening_id, SUM(nilai_total) \n" +
                "FROM it_akun_budgeting\n" +
                "WHERE rekening_id = :rekening \n" +
                "AND branch_id = :unit \n" +
                "AND tahun = :tahun \n" +
                "AND status LIKE :status \n" + approveFlag +
                "GROUP BY rekening_id";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("rekening", rekeningId)
                .setParameter("unit", branchId)
                .setParameter("tahun", tahun)
                .setParameter("status", "%" + status)
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

    public Boolean checkIfSameStatusAndTipe(String branchId, String tahun, String status, String tipe){

        String SQL = "SELECT a.no_budgeting, a.status\n" +
                "FROM it_akun_budgeting a\n" +
                "INNER JOIN im_akun_kode_rekening b ON b.rekening_id = a.rekening_id\n" +
                "WHERE a.tahun = :tahun \n" +
                "AND a.branch_id = :branch \n" +
                "AND a.status ILIKE :status \n" +
                "AND b.tipe_budgeting = :tipe \n" +
                "LIMIT 1";

        List<Object[]> resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("tahun", tahun)
                .setParameter("branch", branchId)
                .setParameter("status", "%" + status)
                .setParameter("tipe", tipe)
                .list();

        if (resuts.size() > 0){
            return true;
        }
        return false;
    }

    public String checkLastTipeOfBudgeting(){
        String SQL = "SELECT no_budgeting, tipe FROM it_akun_budgeting LIMIT 1";
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        if (results.size() > 0){
            for (Object[] obj : results){
                return obj[1].toString();
            }
        }
        return "";
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_budgeting')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public Budgeting getCheckTransaksi(String branchId, String tahun){

        String SQL = "SELECT branch_id, tahun, status, last_update, last_update_who FROM it_akun_budgeting\n" +
                "WHERE branch_id = :unit\n" +
                "AND tahun = :tahun\n" +
                "ORDER BY last_update DESC\n" +
                "LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", branchId)
                .setParameter("tahun", tahun)
                .list();

        Budgeting budgeting = new Budgeting();
        if (results.size() > 0){
            for (Object[] obj : results){
                budgeting.setBranchId(obj[0].toString());
                budgeting.setTahun(obj[1].toString());
                budgeting.setStatus(obj[2].toString());
                budgeting.setLastUpdate((Timestamp) obj[3]);
                budgeting.setLastUpdateWho(obj[4].toString());
            }
        }

        return budgeting;
    }

    public Budgeting getCheckTransaksiWithTipeBudgeting(String branchId, String tahun, String tipeBudgeting){

        String SQL = "SELECT branch_id, tahun, status, a.last_update, a.last_update_who FROM it_akun_budgeting a\n" +
                "INNER JOIN im_akun_kode_rekening b ON b.rekening_id = a.rekening_id\n" +
                "WHERE branch_id = :unit \n" +
                "AND a.tahun = :tahun \n" +
                "AND b.tipe_budgeting = :tipe \n" +
                "ORDER BY a.last_update DESC\n" +
                "LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", branchId)
                .setParameter("tahun", tahun)
                .setParameter("tipe", tipeBudgeting)
                .list();

        Budgeting budgeting = new Budgeting();
        if (results.size() > 0){
            for (Object[] obj : results){
                budgeting.setBranchId(obj[0].toString());
                budgeting.setTahun(obj[1].toString());
                budgeting.setStatus(obj[2].toString());
                budgeting.setLastUpdate((Timestamp) obj[3]);
                budgeting.setLastUpdateWho(obj[4].toString());
            }
        }

        return budgeting;
    }

    public String checkNilaiDasarByTahun(String tahun){

        String SQL = "SELECT id FROM it_akun_budgeting_nilai_dasar\n" +
                "    WHERE tahun = :tahun \n" +
                "    AND flag = 'Y'\n" +
                "    ORDER BY created_date DESC LIMIT 1";

        List<Object> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("tahun", tahun)
                .list();

        Budgeting budgeting = new Budgeting();
        if (results.size() > 0){
            return "Y";
        }
        return "N";
    }



    public String getNoSebelumnya(String tahun, String branchId, String rekeningId, String status){
        String SQL = "SELECT no_budgeting, status, rekening_id\n" +
                "FROM it_akun_budgeting\n" +
                "WHERE tahun = :tahun\n" +
                "AND branch_id = :unit\n" +
                "AND rekening_id = :rekening\n" +
                "AND status ILIKE :status";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("tahun", tahun)
                .setParameter("unit", branchId)
                .setParameter("rekening", rekeningId)
                .setParameter("status", "%" + status)
                .list();

        if (results.size() > 0){
            for (Object[] obj : results){
                return obj[0].toString();
            }
        }

        return "";
    }

    public SaldoAkhir getSaldoAkhirLastPeriod(String tahun, String rekeningId, String branchId){

        String SQL = "SELECT rekening_id, periode, saldo_akhir_id FROM it_akun_saldo_akhir WHERE periode LIKE :tahun\n" +
                "AND rekening_id = :rekeningId  \n" +
                "AND branch_id = :unit\n" +
                "ORDER BY periode DESC LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("rekeningId", rekeningId)
                .setParameter("tahun", "%"+tahun)
                .setParameter("unit", branchId)
                .list();

        if (results.size() > 0){
            for (Object[] obj : results){

                SaldoAkhir saldoAkhir = new SaldoAkhir();
                saldoAkhir.setRekeningId(obj[0].toString());
                saldoAkhir.setPeriode(obj[1].toString());
                saldoAkhir.setSaldoAkhirId(obj[2].toString());

                String[] arrSt = saldoAkhir.getPeriode().split("-",2);
                if (arrSt.length > 0){
                    saldoAkhir.setBulan(Integer.valueOf(arrSt[0].toString()));
                }

                return saldoAkhir;
            }
        }

        return null;
    }

    public List<Budgeting> getListLabaRugi(String tahun, String unit, String status){

        if (unit == null || "".equalsIgnoreCase(unit))
            unit = "%";

        String SQL = "SELECT \n" +
                "a.tahun,\n" +
                "a.jenis,\n" +
                "SUM(a.nilai) as nilai\n" +
                "FROM\n" +
                "(\n" +
                "\tSELECT \n" +
                "\tbg.tahun,\n" +
                "\t'Pendapatan' as jenis,\n" +
                "\tSUM(bg.nilai_total) as nilai\n" +
                "\tFROM it_akun_budgeting bg\n" +
                "\tINNER JOIN im_akun_kode_rekening kd ON kd.rekening_id = bg.rekening_id\n" +
                "\tWHERE kd.tipe_budgeting = 'pendapatan'\n" +
                "\tAND bg.status ILIKE :status\n" +
                "\tAND bg.branch_id ILIKE :unit\n" +
                "\tAND bg.tahun = :tahun\n" +
                "\tAND kd.level = '1'\n" +
                "\tGROUP BY \n" +
                "\tbg.tahun,\n" +
                "\tbg.branch_id\n" +
                "\tUNION ALL\n" +
                "\tSELECT \n" +
                "\tbg.tahun,\n" +
                "\t'Biaya' as jenis,\n" +
                "\tSUM(bg.nilai_total) as nilai\n" +
                "\tFROM it_akun_budgeting bg\n" +
                "\tINNER JOIN im_akun_kode_rekening kd ON kd.rekening_id = bg.rekening_id\n" +
                "\tWHERE kd.tipe_budgeting != 'pendapatan'\n" +
                "\tAND bg.status ILIKE :status\n" +
                "\tAND bg.branch_id ILIKE :unit\n" +
                "\tAND bg.tahun = :tahun\n" +
                "\tAND kd.level = '1'\n" +
                "\tGROUP BY \n" +
                "\tbg.tahun,\n" +
                "\tbg.branch_id\n" +
                ")a\n" +
                "GROUP BY\n" +
                "a.tahun, a.jenis\n" +
                "ORDER BY a.jenis DESC";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", unit)
                .setParameter("tahun", tahun)
                .setParameter("status", status)
                .list();

        List<Budgeting> budgetingList = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                Budgeting bg = new Budgeting();
                bg.setTahun(obj[0].toString());
                bg.setTipeBudgeting(obj[1].toString());
                bg.setNilaiTotal(obj[2] == null ? new BigDecimal(0) : (BigDecimal) obj[2]);
                budgetingList.add(bg);
            }
        }

        return budgetingList;
    }
}
