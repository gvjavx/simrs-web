package com.neurix.akuntansi.transaksi.budgetingperhitungan.dao;

import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ItAkunPerhitunganBudgetingEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ParameterBudgeting;
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
        String sId = String.format("%05d", iter.next());
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
                "\tINNER JOIN (SELECT * FROM im_simrs_kategori_tindakan WHERE id_kategori_tindakan NOT IN ('KAT00000001','KAT00000002','KAT00000003','KAT00000041')) kt ON kt.id_kategori_tindakan = mt.id_kategori_tindakan\n" +
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
                .setParameter("tahun", new Double(tahun))
                .setParameter("bulan", new Double(bulan))
                .list();

        List<PerhitunganBudgeting> perhitunganBudgetings = new ArrayList<>();
        if (results.size() > 0){
            PerhitunganBudgeting perhitunganBudgeting;
            for (Object[] obj : results){
                perhitunganBudgeting = new PerhitunganBudgeting();
                perhitunganBudgeting.setNamaTindakan(obj[0].toString());
                perhitunganBudgeting.setHarga(obj[1] == null ? new BigDecimal(0) : (BigDecimal) obj[1]);
                perhitunganBudgeting.setHargaDiskon(obj[2] == null ? new BigDecimal(0) : (BigDecimal) obj[2]);
                perhitunganBudgeting.setQty(obj[3] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[3]);
                perhitunganBudgeting.setTotalHarga(obj[4] == null ? new BigDecimal(4) : (BigDecimal) obj[4]);
                perhitunganBudgeting.setTotalHargaDiskon(obj[5] == null ? new BigDecimal(0) : (BigDecimal) obj[5]);
                perhitunganBudgetings.add(perhitunganBudgeting);
            }
        }
        return perhitunganBudgetings;
    }

    public List<PerhitunganBudgeting> getListPerhitunganPendapatanTindakanRi(String branchId, String tahun, String bulan){

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
                "\tINNER JOIN (SELECT * FROM im_simrs_kategori_tindakan WHERE id_kategori_tindakan IN ('KAT00000001','KAT00000002','KAT00000003','KAT00000041')) kt ON kt.id_kategori_tindakan = mt.id_kategori_tindakan\n" +
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
                .setParameter("tahun", new Double(tahun))
                .setParameter("bulan", new Double(bulan))
                .list();

        List<PerhitunganBudgeting> perhitunganBudgetings = new ArrayList<>();
        if (results.size() > 0){
            PerhitunganBudgeting perhitunganBudgeting;
            for (Object[] obj : results){
                perhitunganBudgeting = new PerhitunganBudgeting();
                perhitunganBudgeting.setNamaTindakan(obj[0].toString());
                perhitunganBudgeting.setHarga(obj[1] == null ? new BigDecimal(0) : (BigDecimal) obj[1]);
                perhitunganBudgeting.setHargaDiskon(obj[2] == null ? new BigDecimal(0) : (BigDecimal) obj[2]);
                perhitunganBudgeting.setQty(obj[3] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[3]);
                perhitunganBudgeting.setTotalHarga(obj[4] == null ? new BigDecimal(4) : (BigDecimal) obj[4]);
                perhitunganBudgeting.setTotalHargaDiskon(obj[5] == null ? new BigDecimal(0) : (BigDecimal) obj[5]);
                perhitunganBudgetings.add(perhitunganBudgeting);
            }
        }
        return perhitunganBudgetings;
    }


    public List<PerhitunganBudgeting> getListPendapatanObatPeriksa(String branchId, String tahun, String bulan){

        String SQL = "SELECT\n" +
                "a.nama_obat, \n" +
                "a.harga_net,\n" +
                "CAST (SUM (a.qty) AS bigint) as qty,\n" +
                "SUM (a.total) as total\n" +
                "FROM \n" +
                "(\n" +
                "\tSELECT\n" +
                "\tho.nama_obat, \n" +
                "\tho.harga_net,\n" +
                "\todb.qty,\n" +
                "\tho.harga_net * odb.qty AS total\n" +
                "\tFROM mt_simrs_harga_obat ho\n" +
                "\tINNER JOIN mt_simrs_transaksi_obat_detail od On od.id_obat = ho.id_obat\n" +
                "\tINNER JOIN \n" +
                "\t(\n" +
                "\t\tSELECT \n" +
                "\t\tid_transaksi_obat_detail,\n" +
                "\t\tSUM(qty_approve) as QTY\n" +
                "\t\tFROM mt_simrs_transaksi_obat_detail_batch \n" +
                "\t\tWHERE\n" +
                "\t\tapprove_flag = 'Y'\n" +
                "\t\tGROUP BY\n" +
                "\t\tid_transaksi_obat_detail\n" +
                "\t) odb ON odb.id_transaksi_obat_detail = od.id_transaksi_obat_detail\n" +
                "\tINNER JOIN mt_simrs_approval_transaksi_obat ap ON ap.id_approval_obat = od.id_approval_obat\n" +
                "\tINNER JOIN mt_simrs_permintaan_resep pr ON pr.id_approval_obat = od.id_approval_obat\n" +
                "\tINNER JOIN it_simrs_header_detail_checkup hdc ON hdc.id_detail_checkup = pr.id_detail_checkup\n" +
                "\tINNER JOIN it_akun_jurnal jd ON jd.no_jurnal = hdc.no_jurnal\n" +
                "\tINNER JOIN (SELECT * FROM im_simrs_pelayanan WHERE tipe_pelayanan = 'apotek') ml ON ml.id_pelayanan = pr.tujuan_pelayanan\n" +
                "\tWHERE hdc.branch_id = :unit\n" +
                "\tAND EXTRACT(YEAR from jd.tanggal_jurnal) = :tahun\n" +
                "\tAND EXTRACT(MONTH from jd.tanggal_jurnal) <= :bulan\n" +
                ")a\n" +
                "GROUP BY\n" +
                "a.nama_obat, \n" +
                "a.harga_net;";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", branchId)
                .setParameter("tahun", new Double(tahun))
                .setParameter("bulan", new Double(bulan))
                .list();

        List<PerhitunganBudgeting> perhitunganBudgetings = new ArrayList<>();
        if (results.size() > 0){
            PerhitunganBudgeting perhitunganBudgeting;
            for (Object[] obj : results){
                perhitunganBudgeting = new PerhitunganBudgeting();
                perhitunganBudgeting.setNamaTindakan(obj[0].toString());
                perhitunganBudgeting.setHarga(obj[1] == null ? new BigDecimal(0) : (BigDecimal) obj[1]);
                perhitunganBudgeting.setQty(obj[2] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[2]);
                perhitunganBudgeting.setTotalHarga(obj[3] == null ? new BigDecimal(0) : (BigDecimal) obj[3]);
                perhitunganBudgetings.add(perhitunganBudgeting);
            }
        }
        return perhitunganBudgetings;
    }

    public List<PerhitunganBudgeting> getListPendapatanObatPeriksaRi(String branchId, String tahun, String bulan){

        String SQL = "SELECT\n" +
                "a.nama_obat, \n" +
                "a.harga_net,\n" +
                "CAST (SUM (a.qty) AS bigint) as qty,\n" +
                "SUM (a.total) as total\n" +
                "FROM \n" +
                "(\n" +
                "\tSELECT\n" +
                "\tho.nama_obat, \n" +
                "\tho.harga_net,\n" +
                "\todb.qty,\n" +
                "\tho.harga_net * odb.qty AS total\n" +
                "\tFROM mt_simrs_harga_obat ho\n" +
                "\tINNER JOIN mt_simrs_transaksi_obat_detail od On od.id_obat = ho.id_obat\n" +
                "\tINNER JOIN \n" +
                "\t(\n" +
                "\t\tSELECT \n" +
                "\t\tid_transaksi_obat_detail,\n" +
                "\t\tSUM(qty_approve) as QTY\n" +
                "\t\tFROM mt_simrs_transaksi_obat_detail_batch \n" +
                "\t\tWHERE\n" +
                "\t\tapprove_flag = 'Y'\n" +
                "\t\tGROUP BY\n" +
                "\t\tid_transaksi_obat_detail\n" +
                "\t) odb ON odb.id_transaksi_obat_detail = od.id_transaksi_obat_detail\n" +
                "\tINNER JOIN mt_simrs_approval_transaksi_obat ap ON ap.id_approval_obat = od.id_approval_obat\n" +
                "\tINNER JOIN mt_simrs_permintaan_resep pr ON pr.id_approval_obat = od.id_approval_obat\n" +
                "\tINNER JOIN it_simrs_header_detail_checkup hdc ON hdc.id_detail_checkup = pr.id_detail_checkup\n" +
                "\tINNER JOIN it_akun_jurnal jd ON jd.no_jurnal = hdc.no_jurnal\n" +
                "\tINNER JOIN (SELECT * FROM im_simrs_pelayanan WHERE tipe_pelayanan = 'apotek_ri') ml ON ml.id_pelayanan = pr.tujuan_pelayanan\n" +
                "\tWHERE hdc.branch_id = :unit\n" +
                "\tAND EXTRACT(YEAR from jd.tanggal_jurnal) = :tahun\n" +
                "\tAND EXTRACT(MONTH from jd.tanggal_jurnal) <= :bulan\n" +
                ")a\n" +
                "GROUP BY\n" +
                "a.nama_obat, \n" +
                "a.harga_net;";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", branchId)
                .setParameter("tahun", new Double(tahun))
                .setParameter("bulan", new Double(bulan))
                .list();

        List<PerhitunganBudgeting> perhitunganBudgetings = new ArrayList<>();
        if (results.size() > 0){
            PerhitunganBudgeting perhitunganBudgeting;
            for (Object[] obj : results){
                perhitunganBudgeting = new PerhitunganBudgeting();
                perhitunganBudgeting.setNamaTindakan(obj[0].toString());
                perhitunganBudgeting.setHarga(obj[1] == null ? new BigDecimal(0) : (BigDecimal) obj[1]);
                perhitunganBudgeting.setQty(obj[2] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[2]);
                perhitunganBudgeting.setTotalHarga(obj[3] == null ? new BigDecimal(0) : (BigDecimal) obj[3]);
                perhitunganBudgetings.add(perhitunganBudgeting);
            }
        }
        return perhitunganBudgetings;
    }

    public List<Budgeting> getSumNilaiBudgeting(String tahun){

        String SQL = "SELECT\n" +
                "br.branch_id,\n" +
                "br.branch_name,\n" +
                "SUM(np.nilai_total) as nilai_total\n" +
                "FROM im_branches br\n" +
                "LEFT JOIN (SELECT * FROM it_akun_nilai_parameter_budgeting WHERE tahun = :tahun ) np ON np.branch_id = br.branch_id\n" +
                "WHERE br.branch_id LIKE '%'\n" +
                "AND br.flag = 'Y'\n" +
                "GROUP BY \n" +
                "br.branch_id,\n" +
                "br.branch_name";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("tahun" , tahun)
                .list();

        List<Budgeting> budgetings = new ArrayList<>();
        for (Object[] obj : results){
            Budgeting budgeting = new Budgeting();
            budgeting.setBranchId(obj[0].toString());
            budgeting.setBranchName(obj[1].toString());
            budgeting.setNilaiTotal(obj[2] == null ? new BigDecimal(0) : (BigDecimal) obj[2]);
            budgeting.setTahun(tahun);
            budgeting.setFlagDisable(disableByFoundBudgeting(budgeting.getBranchId(), tahun));
            budgetings.add(budgeting);
        }

        return budgetings;
    }

    public String disableByFoundBudgeting(String branchId, String tahun){

        String SQL = "SELECT no_budgeting, branch_id, tahun\n" +
                "FROM it_akun_budgeting \n" +
                "WHERE branch_id = :unit \n" +
                "AND tahun = :tahun \n" +
                "ORDER BY last_update DESC LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("tahun", tahun)
                .setParameter("unit", branchId)
                .list();

        if (results.size() > 0){
            return "Y";
        }
        return "N";
    }

    public List<ParameterBudgeting> getListMasterByIdKategori(String idKategori){

        String SQL = "SELECT a.master_id, b.nama\n" +
                "FROM im_akun_parameter_budgeting a\n" +
                "LEFT JOIN im_akun_master b ON b.nomor_master = a.master_id\n" +
                "WHERE a.id_kategori_budgeting = :idKategori \n" +
                "GROUP BY a.master_id, b.nama";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idKategori", idKategori)
                .list();

        List<ParameterBudgeting> budgetingList = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                ParameterBudgeting bg = new ParameterBudgeting();
                bg.setMasterId(obj[0].toString());
                bg.setNamaMaster(obj[1].toString() == null ? "" : obj[1].toString());
                budgetingList.add(bg);
            }
        }

        return budgetingList;
    }

    public List<ParameterBudgeting> getListDivisiByIdKategoriAndMaster(String idKategori, String masterId){

        String SQL = "SELECT a.divisi_id, b.position_name, a.id\n" +
                "FROM im_akun_parameter_budgeting a\n" +
                "INNER JOIN im_position b ON b.kodering = a.divisi_id\n" +
                "WHERE a.id_kategori_budgeting = :kategori \n" +
                "AND a.master_id = :master \n" +
                "GROUP BY a.divisi_id, b.position_name, a.id";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("kategori", idKategori)
                .setParameter("master", masterId)
                .list();

        List<ParameterBudgeting> budgetingList = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                ParameterBudgeting bg = new ParameterBudgeting();
                bg.setDivisiId(obj[0].toString());
                bg.setNamaDivisi(obj[1].toString());
                bg.setId(obj[2].toString());
                budgetingList.add(bg);
            }
        }
        return budgetingList;
    }

}
