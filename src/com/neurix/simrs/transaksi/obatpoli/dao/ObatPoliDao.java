package com.neurix.simrs.transaksi.obatpoli.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.hargaterakhir.model.MtSimrsHargaTerakhirEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObatPerKonsumen;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsObatPoliEntity;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 10/12/2019.
 */
public class ObatPoliDao extends GenericDao<MtSimrsObatPoliEntity,String> {

    @Override
    protected Class<MtSimrsObatPoliEntity> getEntityClass() {
        return MtSimrsObatPoliEntity.class;
    }

    @Override
    public List<MtSimrsObatPoliEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsObatPoliEntity.class);

        if (mapCriteria.get("id_barang") != null) {
            criteria.add(Restrictions.eq("primaryKey.idBarang", mapCriteria.get("id_barang")));
        }
        if (mapCriteria.get("id_pelayanan") != null) {
            criteria.add(Restrictions.eq("primaryKey.idPelayanan", mapCriteria.get("id_pelayanan")));
        }
        if (mapCriteria.get("id_obat") != null) {
            criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat")));
        }
        if (mapCriteria.get("branch_id") != null) {
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id")));
        }
        if (mapCriteria.get("id_pabrik") != null) {
            criteria.add(Restrictions.eq("idPabrik", mapCriteria.get("id_pabrik")));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }
        if (mapCriteria.get("asc") != null){
            criteria.addOrder(Order.asc("createdDate"));
        }
        if (mapCriteria.get("exp") != null){
            criteria.addOrder(Order.asc("expiredDate"));
        }

        //criteria.addOrder(Order.asc("primaryKey.idObat"));
        List<MtSimrsObatPoliEntity> results = criteria.list();
        return results;
    }

    public List<PermintaanObatPoli> cekIdObatInTransaksi(PermintaanObatPoli bean){

        String idObat      = "%";
        String idPelayanan = "%";
        String branchId    = "%";

        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            idObat = bean.getIdObat();
        }

        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
            idPelayanan = bean.getIdPelayanan();
        }

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branchId = bean.getBranchId();
        }

        String SQL = "SELECT b.id_obat, a.id_pelayanan, a.branch_id " +
                "FROM mt_simrs_approval_transaksi_obat a\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail b " +
                "ON a.id_approval_obat = b.id_approval_obat\n" +
                "WHERE a.id_pelayanan LIKE :idPelayanan " +
                "AND a.branch_id LIKE :branchId " +
                "AND b.id_obat LIKE :idObat " +
                "AND a.flag = 'Y' AND b.flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("branchId", branchId)
                .setParameter("idObat", idObat)
                .list();

        List<PermintaanObatPoli> obatPoliList = new ArrayList<>();

        if (results.size() > 0)
        {
            PermintaanObatPoli permintaanObatPoli;
            for (Object[] obj : results)
            {
                permintaanObatPoli = new PermintaanObatPoli();
                permintaanObatPoli.setIdObat(obj[0].toString());
                permintaanObatPoli.setIdPelayanan(obj[1].toString());
                permintaanObatPoli.setBranchId(obj[2].toString());
                obatPoliList.add(permintaanObatPoli);
            }
        }

        return obatPoliList;
    }

    public List<PermintaanObatPoli> cekIdObatInTransaksiRequestGudang(PermintaanObatPoli bean){

        String idObat      = "%";
        String idPelayanan = "%";
        String branchId    = "%";

        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            idObat = bean.getIdObat();
        }

        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
            idPelayanan = bean.getIdPelayanan();
        }

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branchId = bean.getBranchId();
        }

        String SQL = "SELECT b.id_obat, a.id_pelayanan, a.branch_id \n" +
                "FROM mt_simrs_approval_transaksi_obat a\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                "INNER JOIN mt_simrs_permintaan_obat_poli pol ON pol.id_approval_obat = a.id_approval_obat\n" +
                "WHERE a.id_pelayanan LIKE :idPelayanan " +
                "AND a.branch_id LIKE :branchId " +
                "AND b.id_obat LIKE :idObat " +
                "AND a.flag = 'Y' AND b.flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("branchId", branchId)
                .setParameter("idObat", idObat)
                .list();

        List<PermintaanObatPoli> obatPoliList = new ArrayList<>();

        if (results.size() > 0)
        {
            PermintaanObatPoli permintaanObatPoli;
            for (Object[] obj : results)
            {
                permintaanObatPoli = new PermintaanObatPoli();
                permintaanObatPoli.setIdObat(obj[0].toString());
                permintaanObatPoli.setIdPelayanan(obj[1].toString());
                permintaanObatPoli.setBranchId(obj[2].toString());
                obatPoliList.add(permintaanObatPoli);
            }
        }

        return obatPoliList;
    }

    public List<ObatPoli> getTujuanPelyanan(ObatPoli bean){

        String idPelayanan = "%";

        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
            idPelayanan = bean.getIdPelayanan();
        }

        String SQL = "SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "a.branch_id,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan \n" +
                "WHERE a.id_pelayanan NOT LIKE :idPelayanan AND a.flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPelayanan", idPelayanan)
                .list();

        List<ObatPoli> obatPoliList = new ArrayList<>();

        if (results.size() > 0)
        {
            ObatPoli obatPoli;
            for (Object[] obj : results)
            {
                obatPoli = new ObatPoli();
                obatPoli.setIdPelayanan(obj[0].toString());
                obatPoli.setNamaPelayanan(obj[1].toString());
                obatPoliList.add(obatPoli);
            }
        }

        return obatPoliList;
    }

    public List<String> getIdObatGroup(String idPelayanan, String branchId){

        String SQL = "SELECT id_obat, id_pelayanan\n" +
                "FROM mt_simrs_obat_poli\n" +
                "WHERE id_pelayanan = :idPelayanan \n" +
                "AND branch_id = :branchId \n" +
                "GROUP BY id_obat, id_pelayanan";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("branchId", branchId)
                .list();

        List<String> stringList = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                stringList.add(obj[0].toString());
            }
        }
        return stringList;
    }

    // flagBpjs adalah = jenis pasien
    public List<ObatPoli> getIdObatGroupPoli(ObatPoli bean){
        List<ObatPoli> obatPoliList = new ArrayList<>();
        if(bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan()) && bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){

            String flag = "";
            if("bpjs".equalsIgnoreCase(bean.getJenisPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getJenisPasien())){
                flag = "WHERE flag_bpjs = 'Y' AND id_barang IS NOT NULL\n";
            }else{
                flag = "WHERE flag_bpjs != 'Y' AND id_barang IS NOT NULL\n";
            }

            String SQL = "SELECT \n" +
                    "b.id_pelayanan,\n" +
                    "a.id_obat,\n" +
                    "a.nama_obat,\n" +
                    "b.box,\n" +
                    "b.lembar,\n" +
                    "b.biji,\n" +
                    "a.lembar_per_box,\n" +
                    "a.biji_per_lembar,\n" +
                    "a.flag_kronis\n" +
                    "FROM (\n" +
                    "\t\tSELECT \n" +
                    "\t\ta.id_obat,\n" +
                    "\t\ta.nama_obat,\n" +
                    "\t\ta.flag_kronis,\n" +
                    "\t\ta.lembar_per_box,\n" +
                    "\t\ta.biji_per_lembar\n" +
                    "\t\tFROM im_simrs_header_obat a\n" +
                    "\t\tINNER JOIN (\n" +
                    "\t\t\tSELECT id_barang, id_obat, flag_bpjs  \n" +
                    "\t\t\tFROM im_simrs_obat \n" + flag +
                    "\t\t) b ON b.id_obat = a.id_obat\n" +
                    "\t\tINNER JOIN mt_simrs_obat_poli ob ON ob.id_barang = b.id_barang\n" +
                    "\t\tGROUP BY \n" +
                    "\t\ta.id_obat,\n" +
                    "\t\ta.nama_obat,\n" +
                    "\t\ta.flag_kronis,\n" +
                    "\t\ta.lembar_per_box,\n" +
                    "\t\ta.biji_per_lembar,\n" +
                    "\t\ta.flag_bpjs\n" +
                    ") a\n" +
                    "INNER JOIN (\n" +
                    "\tSELECT\n" +
                    "\tid_obat,\n" +
                    "\tid_pelayanan,\n" +
                    "\tbranch_id,\n" +
                    "\tSUM(qty_box) as box,\n" +
                    "\tSUM(qty_lembar) as lembar,\n" +
                    "\tSUM(qty_biji) as biji\n" +
                    "\tFROM mt_simrs_obat_poli\n" +
                    "\tWHERE id_pelayanan = :idPelayanan \n" +
                    "\tAND branch_id = :branchId \n" +
                    "\tGROUP BY id_obat, id_pelayanan, branch_id\n" +
                    "\tHAVING SUM(qty_box) > 0 OR SUM(qty_lembar) > 0 OR SUM(qty_biji) > 0 \n" +
                    ") b ON a.id_obat = b.id_obat\n" +
                    "LEFT JOIN (\n" +
                    "\tSELECT\n" +
                    "\tid_obat,\n" +
                    "\tid_jenis_obat\n" +
                    "\tFROM im_simrs_obat_gejala \n" +
                    ")d ON d.id_obat = b.id_obat \n" +
                    "GROUP BY \n" +
                    "b.id_pelayanan,\n" +
                    "a.id_obat,\n" +
                    "a.nama_obat,\n" +
                    "b.box,\n" +
                    "b.lembar,\n" +
                    "b.biji,\n" +
                    "a.lembar_per_box,\n" +
                    "a.biji_per_lembar,\n" +
                    "a.flag_kronis";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPelayanan", bean.getIdPelayanan())
                    .setParameter("branchId", bean.getBranchId())
                    .list();

            if (results.size() > 0){
                for (Object[] obj : results){
                    ObatPoli obatPoli = new ObatPoli();
                    obatPoli.setIdPelayanan(obj[0] == null ? "" : obj[0].toString());
                    obatPoli.setIdObat(obj[1] == null ? "" : obj[1].toString());
                    obatPoli.setNamaObat(obj[2] == null ? "" : obj[2].toString());
                    obatPoli.setQtyBox(obj[3] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[3].toString()));
                    obatPoli.setQtyLembar(obj[4] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[4].toString()));
                    obatPoli.setQtyBiji(obj[5] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[5].toString()));
                    obatPoli.setLembarPerBox(obj[6] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[6].toString()));
                    obatPoli.setBijiPerLembar(obj[7] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[7].toString()));
                    obatPoli.setFlagKronis(obj[8] == null ? "" : obj[8].toString());

                    // Sigit, 2021-04-29. penambahan untuk mencari data per konsumen pada resep;
                    HeaderCheckup headerDetailCheckup = getHeaderCheckupData(bean.getIdDetailCheckup());
                    if (headerDetailCheckup != null){
                        HargaObatPerKonsumen perKonsumen = getDataHargaPerKonsumen(obatPoli.getIdObat(), bean.getBranchId(), headerDetailCheckup.getIdJenisPeriksaPasien(), headerDetailCheckup.getIdAsuransi());
                        if (perKonsumen != null){
                            obatPoli.setHarga(perKonsumen.getHargaJual().toString());
                        }
                    }

                    if (obatPoli.getHarga() != null && !"".equalsIgnoreCase(obatPoli.getHarga())){
                        obatPoliList.add(obatPoli);
                    }
                }
            }
        }
        return obatPoliList;
    }

    private HeaderCheckup getHeaderCheckupData(String idHeaderCheckup){

        String SQL = "SELECT \n" +
                "id_detail_checkup, \n" +
                "id_asuransi,\n" +
                "id_jenis_periksa_pasien\n" +
                "FROM \n" +
                "it_simrs_header_detail_checkup\n" +
                "WHERE id_detail_checkup = '"+idHeaderCheckup+"'";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            Object[] obj = list.get(0);
            HeaderCheckup headerCheckup = new HeaderCheckup();
            headerCheckup.setIdDetailCheckup(obj[0].toString());
            headerCheckup.setIdAsuransi(obj[1] != null ? obj[1].toString() : null);
            headerCheckup.setIdJenisPeriksaPasien(obj[2].toString());
            return headerCheckup;
        }

        return null;
    }

    public HeaderCheckup getHeaderCheckupDataByApprovalResep(String idApproval){

        String SQL = "SELECT \n" +
                "hdc.id_detail_checkup, \n" +
                "hdc.id_asuransi,\n" +
                "hdc.id_jenis_periksa_pasien, \n" +
                "pr.branch_id \n" +
                "FROM \n" +
                "it_simrs_header_detail_checkup hdc \n" +
                "INNER JOIN mt_simrs_permintaan_resep pr ON pr.id_detail_checkup = hdc.id_detail_checkup \n" +
                "WHERE pr.id_approval_obat = '"+idApproval+"'";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            Object[] obj = list.get(0);
            HeaderCheckup headerCheckup = new HeaderCheckup();
            headerCheckup.setIdDetailCheckup(obj[0].toString());
            headerCheckup.setIdAsuransi(obj[1] != null ? obj[1].toString() : null);
            headerCheckup.setIdJenisPeriksaPasien(obj[2].toString());
            headerCheckup.setBranchId(obj[3] != null ? obj[3].toString() : null);
            return headerCheckup;
        }

        return null;
    }

    public HeaderCheckup getHeaderCheckupDataByPermintaanResep(String idPermintaanResep){

        String SQL = "SELECT \n" +
                "hdc.id_detail_checkup, \n" +
                "hdc.id_asuransi,\n" +
                "hdc.id_jenis_periksa_pasien, \n" +
                "pr.branch_id \n" +
                "FROM \n" +
                "it_simrs_header_detail_checkup hdc \n" +
                "INNER JOIN mt_simrs_permintaan_resep pr ON pr.id_detail_checkup = hdc.id_detail_checkup \n" +
                "WHERE pr.id_permintaan_resep = '"+idPermintaanResep+"'";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            Object[] obj = list.get(0);
            HeaderCheckup headerCheckup = new HeaderCheckup();
            headerCheckup.setIdDetailCheckup(obj[0].toString());
            headerCheckup.setIdAsuransi(obj[1] != null ? obj[1].toString() : null);
            headerCheckup.setIdJenisPeriksaPasien(obj[2].toString());
            headerCheckup.setBranchId(obj[3] != null ? obj[3].toString() : null);
            return headerCheckup;
        }

        return null;
    }

    /**
     * 2021-07-01, Sigit
     * Method untuk merubah ke default harga (umum / bpjs) pada masing - masing jenis konsumen jika belum diset
     * pada harga obat per konsumen;
     * @param idObat
     * @param branchId
     * @param jenisKonsumen
     * @param idRekanan
     * @return
     */
    private String changeToUmumOrBpjsIfNotAvailableYetInHargaObatPerkonsumen(String idObat, String branchId, String jenisKonsumen, String idRekanan){

        String sqlRekanan = "";
        if (idRekanan != null && !"".equalsIgnoreCase(idRekanan)){
            sqlRekanan = "AND hopk.id_rekanan = '"+idRekanan+"' \n";
        }

        String SQL = "SELECT \n" +
                "hopk.id_harga_obat \n" +
                "FROM (SELECT * FROM mt_simrs_harga_obat_per_konsumen WHERE flag ='Y') hopk\n" +
                "INNER JOIN mt_simrs_harga_terakhir ht ON ht.id = hopk.id_harga_obat \n" +
                "WHERE ht.id_obat = '"+idObat+"'\n" +
                "AND ht.branch_id = '"+branchId+"'\n" +
                "AND hopk.jenis_konsumen = '"+jenisKonsumen+"'\n" + sqlRekanan;

        List<Object> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            return jenisKonsumen;
        } else if (list.size() == 0 && "bpjs_rekanan".equalsIgnoreCase(jenisKonsumen)){
            return "bpjs";
        } else if (list.size() == 0 && "rekanan".equalsIgnoreCase(jenisKonsumen)){
            return "umum";
        } else {
            return "umum";
        }
    }

    public HargaObatPerKonsumen getDataHargaPerKonsumen(String idObat, String branchId, String jenisKonsumen, String idRekanan){

        // 2021-07-01, Sigit
        // set jenis konsumen ke default umum / bpjs jika tidak ditemukan jenis konsumen tersebut pada harga per konsumen
        jenisKonsumen = changeToUmumOrBpjsIfNotAvailableYetInHargaObatPerkonsumen(idObat, branchId, jenisKonsumen, idRekanan);

        String sqlRekanan = "";
        if (idRekanan != null && !"".equalsIgnoreCase(idRekanan) && !"umum".equalsIgnoreCase(jenisKonsumen) && !"bpjs".equalsIgnoreCase(jenisKonsumen)){
            sqlRekanan = "AND hopk.id_rekanan = '"+idRekanan+"' \n";
        }

        String SQL = "SELECT \n" +
                "hopk.id_harga_obat, \n" +
                "ht.branch_id, \n" +
                "hopk.harga_jual, \n" +
                "ht.harga_terakhir, \n" +
                "ht.harga_rata_umum, \n" +
                "ht.harga_terakhir_bpjs, \n" +
                "ht.harga_rata_bpjs \n" +
                "FROM (SELECT * FROM mt_simrs_harga_obat_per_konsumen WHERE flag ='Y') hopk\n" +
                "INNER JOIN mt_simrs_harga_terakhir ht ON ht.id = hopk.id_harga_obat \n" +
                "WHERE ht.id_obat = '"+idObat+"'\n" +
                "AND ht.branch_id = '"+branchId+"'\n" +
                "AND hopk.jenis_konsumen = '"+jenisKonsumen+"'\n" + sqlRekanan;

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            Object[] obj = list.get(0);
            HargaObatPerKonsumen perKonsumen = new HargaObatPerKonsumen();
            perKonsumen.setIdHargaObat(obj[0].toString());
            perKonsumen.setBranchId(obj[1].toString());
            perKonsumen.setHargaJual(objToBigDecimal(obj[2]));

            BigDecimal hargaTerakhirNonBpjs = objToBigDecimal(obj[3]);
            BigDecimal hargaRataNonBpjs     = objToBigDecimal(obj[4]);
            BigDecimal hargaTerakhirBpjs    = objToBigDecimal(obj[5]);
            BigDecimal hargaRataBpjs        = objToBigDecimal(obj[6]);

            if ("bpjs".equalsIgnoreCase(jenisKonsumen)){
                perKonsumen.setHargaTerakhir(hargaTerakhirBpjs);
            } else if ("bpjs_rekanan".equalsIgnoreCase(jenisKonsumen)){
                perKonsumen.setHargaTerakhir(hargaRataBpjs);
            } else if ("rekanan".equalsIgnoreCase(jenisKonsumen)) {
                perKonsumen.setHargaTerakhir(hargaRataNonBpjs);
            } else {
                perKonsumen.setHargaTerakhir(hargaTerakhirNonBpjs);
            }

            return perKonsumen;
        }

        return null;
    }

    private BigDecimal objToBigDecimal(Object obj){
        if (obj == null)
            return new BigDecimal(0);
        else {
            return new BigDecimal(obj.toString()).setScale(BigDecimal.ROUND_UP);
        }
    }

    private Boolean cekIsKhusus(String idDetailCheckup){
        Boolean res = false;
        String SQL = "SELECT\n" +
                "a.id_detail_checkup,\n" +
                "a.id_asuransi\n" +
                "FROM it_simrs_header_detail_checkup a\n" +
                "INNER JOIN im_simrs_rekanan_ops b ON b.id_rekanan_ops = a.id_asuransi\n" +
                "WHERE a.id_detail_checkup = :id\n" +
                "AND b.tipe IN ('ptpn','khusus')";
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idDetailCheckup)
                .list();
        if(null!= results && results.size() > 0){
            res = true;
        }
        return res;
    }

    public List<ObatPoli> getIdObatGroupPoliKandunganSerupa(String idPelayanan, String branchId, String flagBpjs, String[] kandugans){

        List<ObatPoli> obatPoliList = new ArrayList<>();

        if(idPelayanan != null && !"".equalsIgnoreCase(idPelayanan) && branchId != null && !"".equalsIgnoreCase(branchId)){

            String flag = "flag_bpjs != 'Y' ";
            if("bpjs".equalsIgnoreCase(flagBpjs) || "rekanan_bpjs".equalsIgnoreCase(flagBpjs)){
                flag = "flag_bpjs = 'Y' ";
            }

            String SQL = "SELECT\n" +
                    "a.*\n" +
                    "FROM\n" +
                    "(\n" +
                    "\tSELECT\n" +
                    "\ta.id_pelayanan,\n" +
                    "\tb.id_obat,\n" +
                    "\tb.nama_obat,\n" +
                    "\tSUM(a.qty_box) as box,\n" +
                    "\tSUM(a.qty_lembar) as lembar,\n" +
                    "\tSUM(a.qty_biji) as biji,\n" +
                    "\tb.lembar_per_box,\n" +
                    "\tb.biji_per_lembar,\n" +
                    "\tb.flag_kronis,\n" +
                    "\tc.harga_jual\n" +
                    "\tFROM mt_simrs_obat_poli a\n" +
                    "\tINNER JOIN (\n" +
                    "\tSELECT id_obat, nama_obat, lembar_per_box, biji_per_lembar, flag_kronis\n" +
                    "\tFROM im_simrs_obat WHERE "+flag+" GROUP BY id_obat, nama_obat, lembar_per_box, biji_per_lembar, flag_kronis\n" +
                    "\t) b ON a.id_obat = b.id_obat\n" +
                    "\tINNER JOIN mt_simrs_harga_obat c ON a.id_obat = c.id_obat\n" +
                    "\tINNER JOIN im_simrs_kandungan_obat_detail d ON d.id_obat = a.id_obat\n" +
                    "\tWHERE a.id_pelayanan = :idPelayanan \n" +
                    "\tAND a.branch_id = :branchId \n" +
                    "\tAND d.id_kandungan IN :listKandungan \n" +
                    "\tGROUP BY a.id_pelayanan,\n" +
                    "\tb.id_obat,\n" +
                    "\tb.nama_obat,\n" +
                    "\tb.lembar_per_box,\n" +
                    "\tb.biji_per_lembar,\n" +
                    "\tb.flag_kronis,\n" +
                    "\tc.harga_jual\n" +
                    ") a\n" +
                    "WHERE (box, lembar, biji) != (0,0,0)";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPelayanan", idPelayanan)
                    .setParameter("branchId", branchId)
                    .setParameterList("listKandungan", kandugans)
                    .list();

            if (results.size() > 0){
                for (Object[] obj : results){
                    ObatPoli obatPoli = new ObatPoli();
                    obatPoli.setIdPelayanan(obj[0] == null ? "" : obj[0].toString());
                    obatPoli.setIdObat(obj[1] == null ? "" : obj[1].toString());
                    obatPoli.setNamaObat(obj[2] == null ? "" : obj[2].toString());
                    obatPoli.setQtyBox(obj[3] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[3].toString()));
                    obatPoli.setQtyLembar(obj[4] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[4].toString()));
                    obatPoli.setQtyBiji(obj[5] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[5].toString()));
                    obatPoli.setLembarPerBox(obj[6] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[6].toString()));
                    obatPoli.setBijiPerLembar(obj[7] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj[7].toString()));
                    obatPoli.setFlagKronis(obj[8] == null ? "" : obj[8].toString());
                    obatPoli.setHarga(obj[9] == null ? "" : obj[9].toString());
                    obatPoliList.add(obatPoli);
                }
            }
        }
        return obatPoliList;
    }

    public ObatPoli getSumStockObatPoliById(String id, String idPelayanan){

        String SQL = "SELECT \n" +
                "id_obat, \n" +
                "SUM(qty_box) as qty_box, \n" +
                "SUM(qty_lembar) as qty_lembar,\n" +
                "SUM(qty_biji) as qty_biji\n" +
                "FROM mt_simrs_obat_poli \n" +
                "WHERE (qty_box, qty_lembar, qty_biji) != ('0','0','0')\n" +
                "AND id_obat = :id\n" +
                "AND id_pelayanan = :idPoli\n" +
                "GROUP BY id_obat";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .setParameter("idPoli", idPelayanan)
                .list();

        ObatPoli obatPoli = new ObatPoli();
        if (results.size() > 0){
            for (Object[] obj : results){
                obatPoli.setIdObat(obj[0].toString());
                obatPoli.setQtyBox(new BigInteger(String.valueOf(obj[1])));
                obatPoli.setQtyLembar(new BigInteger(String.valueOf(obj[2])));
                obatPoli.setQtyBiji(new BigInteger(String.valueOf(obj[3])));
            }
        }

        return obatPoli;
    }

    public List<ObatPoli> getStokObatPoli(ObatPoli bean){
        List<ObatPoli> obatPoliList = new ArrayList<>();
        if(bean != null){
            String flag = "Y";
            String condition= "";
            if(bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
                flag = bean.getFlag();
            }
            if(bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
                condition += "AND a.id_pelayanan = '"+bean.getIdPelayanan()+"'\n";
            }
            if(bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                condition += "AND a.branch_id = '"+bean.getBranchId()+"'\n";
            }
            if(bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
                condition += "AND a.id_obat LIKE '%"+bean.getIdObat()+"%'\n";
            }
            if(bean.getNamaObat() != null && !"".equalsIgnoreCase(bean.getNamaObat())){
                condition += "AND b.nama_obat ILIKE '%"+bean.getNamaObat()+"%'\n";
            }
            if(bean.getIdBarang() != null && !"".equalsIgnoreCase(bean.getIdBarang())){
                condition += "AND a.id_barang LIKE '%"+bean.getIdBarang()+"%' \n";
            }
            if(bean.getFlagBpjs() != null && "Y".equalsIgnoreCase(bean.getFlagBpjs())){
                condition += "AND c.flag_bpjs = 'Y' \n";
            } else if (bean.getFlagBpjs() != null && "N".equalsIgnoreCase(bean.getFlagBpjs())){
                condition += "AND c.flag_bpjs != 'Y' \n";
            }

            String SQL = "SELECT\n" +
                    "a.id_obat,\n" +
                    "b.nama_obat,\n" +
                    "a.id_barang,\n" +
                    "a.expired_date,\n" +
                    "a.qty_biji,\n" +
                    "a.id_pelayanan,\n" +
                    "a.branch_id,\n" +
                    "c.lembar_per_box,\n" +
                    "c.biji_per_lembar,\n" +
                    "c.flag_bpjs,\n" +
                    "a.qty_lembar,\n" +
                    "a.qty_box\n" +
                    "FROM mt_simrs_obat_poli a\n" +
                    "INNER JOIN im_simrs_header_obat b ON a.id_obat = b.id_obat\n" +
                    "INNER JOIN im_simrs_obat c ON a.id_barang = c.id_barang\n" +
                    "WHERE a.flag = :flag \n" + condition +
                    "AND (a.qty_biji, a.qty_lembar, a.qty_box) > ('0','0','0') ";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("flag", flag)
                    .list();

            if (results.size() > 0){
                for (Object[] obj : results){
                    ObatPoli obatPoli = new ObatPoli();
                    obatPoli.setIdObat(obj[0] != null ? obj[0].toString() : "");
                    obatPoli.setNamaObat(obj[1] != null ? obj[1].toString() : "");
                    obatPoli.setIdBarang(obj[2] != null ? obj[2].toString() : "");
                    obatPoli.setExpiredDate(obj[3] != null ? (Date) obj[3] : null);
                    obatPoli.setQtyBiji(obj[4] != null ? (BigInteger) obj[4] : new BigInteger(String.valueOf("0")));
                    obatPoli.setIdPelayanan(obj[5] != null ? obj[5].toString() : "");
                    obatPoli.setBranchId(obj[6] != null ? obj[6].toString() : "");
                    obatPoli.setLembarPerBox(obj[7] != null ? (BigInteger) obj[7] : new BigInteger(String.valueOf("0")));
                    obatPoli.setBijiPerLembar(obj[8] != null ? (BigInteger) obj[8] : new BigInteger(String.valueOf("0")));
                    obatPoli.setFlagBpjs(obj[9] != null ? obj[9].toString() : "");
                    obatPoli.setQtyLembar(objToBigInteger(obj[10]));
                    obatPoli.setQtyBox(objToBigInteger(obj[11]));

                    BigInteger qtyBox       = obatPoli.getQtyBox().multiply(obatPoli.getLembarPerBox()).multiply(obatPoli.getBijiPerLembar());
                    BigInteger qtyLembar    = obatPoli.getQtyLembar().multiply(obatPoli.getBijiPerLembar());
                    BigInteger qtyBiji      = obatPoli.getQtyBiji();
                    BigInteger totalQty     = qtyBiji.add(qtyLembar).add(qtyBox);

                    obatPoli.setTotalQty(totalQty);
                    obatPoliList.add(obatPoli);
                }
            }
        }
        return obatPoliList;
    }

    private BigInteger objToBigInteger(Object obj){
        if (obj == null)
            return new BigInteger(String.valueOf(0));
        else
            return new BigInteger(obj.toString());
    }

    public ObatPoli getStokObatPoliById(String idObat, String flagBpjs, String idPelayanan){

        String SQL = "SELECT \n" +
                "ab.id_obat,\n" +
                "ab.flag_bpjs,\n" +
                "SUM(ab.qty_box) + SUM(ab.qty_lembar) + SUM(ab.qty_biji) as total_bijian\n" +
                "FROM \n" +
                "(\n" +
                "\tSELECT \n" +
                "\taa.id_obat,\n" +
                "\taa.id_barang,\n" +
                "\taa.flag_bpjs,\n" +
                "\tSUM(aa.qty_box) as qty_box,\n" +
                "\tSUM(aa.qty_lembar) as qty_lembar,\n" +
                "\tSUM(aa.qty_biji) as qty_biji\n" +
                "\tFROM (\n" +
                "\t\tSELECT\n" +
                "\t\ta.id_obat, \n" +
                "\t\ta.id_barang,\n" +
                "\t\tb.flag_bpjs,\n" +
                "\t\ta.qty_box * b.lembar_per_box * b.biji_per_lembar as qty_box,\n" +
                "\t\ta.qty_lembar * b.biji_per_lembar as qty_lembar,\n" +
                "\t\ta.qty_biji \n" +
                "\t\tFROM mt_simrs_obat_poli a\n" +
                "\t\tINNER JOIN im_simrs_obat b ON b.id_barang = a.id_barang\n" +
                "\t\tWHERE a.id_pelayanan = '"+idPelayanan+"'\n" +
                "\t) aa\n" +
                "\tGROUP BY\n" +
                "\taa.id_obat,\n" +
                "\taa.id_barang,\n" +
                "\taa.flag_bpjs\n" +
                ")ab\n" +
                "WHERE ab.id_obat = '"+idObat+"'\n" +
                "AND ab.flag_bpjs = '"+flagBpjs+"'\n" +
                "GROUP BY \n" +
                "ab.id_obat,\n" +
                "ab.flag_bpjs\n" +
                "ORDER BY ab.id_obat";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (results.size() > 0){
            Object[] obj = results.get(0);

            ObatPoli obatPoli = new ObatPoli();
            obatPoli.setIdObat(obj[0].toString());
            obatPoli.setFlagBpjs(obj[1].toString());
            obatPoli.setTotalQty(objToBigInteger(obj[2]));
            return obatPoli;
        }
        return null;
    }
}
