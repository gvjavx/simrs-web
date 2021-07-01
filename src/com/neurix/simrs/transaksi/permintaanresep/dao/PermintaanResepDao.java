package com.neurix.simrs.transaksi.permintaanresep.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObatPerKonsumen;
import com.neurix.simrs.transaksi.obatpoli.dao.ObatPoliDao;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.ObatKronis;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
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
 * Created by Toshiba on 11/12/2019.
 */
public class PermintaanResepDao extends GenericDao<ImSimrsPermintaanResepEntity, String> {
    @Override
    protected Class<ImSimrsPermintaanResepEntity> getEntityClass() {
        return ImSimrsPermintaanResepEntity.class;
    }

    @Override
    public List<ImSimrsPermintaanResepEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPermintaanResepEntity.class);
        if (mapCriteria.get("id_permintaan_resep") != null){
            criteria.add(Restrictions.eq("idPermintaanResep", mapCriteria.get("id_permintaan_resep").toString()));
        }
        if (mapCriteria.get("id_approval_obat") != null){
            criteria.add(Restrictions.eq("idApprovalObat", mapCriteria.get("id_approval_obat").toString()));
        }
        if (mapCriteria.get("id_pasien") != null){
            criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
        }
        if (mapCriteria.get("id_detail_checkup") != null){
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        }
        if (mapCriteria.get("branch_id") != null){
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        List<ImSimrsPermintaanResepEntity> results = criteria.list();
        return results;
    }



    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_permintaan_resep')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<ObatKronis> getLastObatKronis(String idPasien, String flagDiambil){

        String sqlDiambil = "is null";
        if ("Y".equalsIgnoreCase(flagDiambil)){
            sqlDiambil = "is not null";
        }

        String SQL = "SELECT \n" +
                "\thc.id_pasien,\n" +
                "\tdc.id_detail_checkup,\n" +
                "\tpr.id_approval_obat,\n" +
                "\thari_kronis,\n" +
                "\tMAX(dt.created_date) as created_date\n" +
                "\tFROM it_simrs_header_detail_checkup dc\n" +
                "\tINNER JOIN it_simrs_header_checkup hc ON hc.no_checkup = dc.no_checkup\n" +
                "\tINNER JOIN mt_simrs_permintaan_resep pr ON pr.id_detail_checkup = dc.id_detail_checkup\n" +
                "\tINNER JOIN mt_simrs_transaksi_obat_detail dt ON dt.id_approval_obat = pr.id_approval_obat\n" +
                "\tWHERE flag_kronis_diambil "+sqlDiambil+"\n"+
                "\tAND hari_kronis is not null\n" +
                "\tAND hc.id_pasien = :idPasien\n" +
                "\tAND dc.id_jenis_periksa_pasien = 'bpjs'\n" +
                "\tAND dc.status_periksa = '3'\n" +
                "\tGROUP BY\n" +
                "\thc.id_pasien,\n" +
                "\tdc.id_detail_checkup,\n" +
                "\tpr.id_approval_obat,\n" +
                "\thari_kronis LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPasien", idPasien)
                .list();

        List<ObatKronis> obatKronisList = new ArrayList<>();
        ObatKronis obatDetail;
        if (results.size() > 0){
            for (Object[] obj : results){
                obatDetail = new ObatKronis();
                obatDetail.setIdPasien((String) obj[0]);
                obatDetail.setIdDetailCheckup((String) obj[1]);
                obatDetail.setIdApprovalObat((String) obj[2]);
                obatDetail.setIntervalHariKronis((Integer) obj[3]);
                obatDetail.setCreatedDate((Timestamp) obj[4]);
                obatKronisList.add(obatDetail);
            }
        }
        return obatKronisList;
    }

    public List<PermintaanResep> getListResepPasien(String noCheckup, String jenis){
        List<PermintaanResep> rawatList = new ArrayList<>();
        String SQL = "SELECT \n" +
                "a.id_permintaan_resep,\n" +
                "a.id_approval_obat,\n" +
                "a.status,\n" +
                "a.id_detail_checkup,\n" +
                "d.id_pelayanan,\n" +
                "d.nama_pelayanan,\n" +
                "a.created_date\n" +
                "FROM mt_simrs_permintaan_resep a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                "LEFT JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "a.branch_id,"+
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                ") d ON b.id_pelayanan = d.id_pelayanan\n" +
                "WHERE c.no_checkup = :id AND b.id_jenis_periksa_pasien = :jen \n" +
                "ORDER BY a.id_detail_checkup ASC\n";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", noCheckup)
                .setParameter("jen", jenis)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
                PermintaanResep permintaanResep = new PermintaanResep();
                permintaanResep.setIdPermintaanResep(obj[0] != null ? obj[0].toString() : "");
                permintaanResep.setIdApprovalObat(obj[1] != null ? obj[1].toString() : "");
                permintaanResep.setStatus(obj[2] != null ? obj[2].toString() : "");
                permintaanResep.setIdDetailCheckup(obj[3] != null ? obj[3].toString() : "");
                permintaanResep.setIdPelayanan(obj[4] != null ? obj[4].toString() : "");
                permintaanResep.setNamaPelayanan(obj[5] != null ? obj[5].toString() : "");
                permintaanResep.setCreatedDate(obj[6] != null ? (Timestamp) obj[6] : null);
                rawatList.add(permintaanResep);
            }
        }
        return rawatList;
    }

    public List<PermintaanResep> listResepPasienTerakhir(String idPasien, String idPelayanan){

        String SQL = "SELECT \n" +
                "pr.id_permintaan_resep,\n" +
                "pr.id_approval_obat,\n" +
                "pr.id_pasien,\n" +
                "pr.id_detail_checkup,\n" +
                "pr.tgl_antrian,\n" +
                "pr.tujuan_pelayanan,\n" +
                "pr.branch_id,\n" +
                "dc.id_jenis_periksa_pasien \n, " +
                "dc.id_asuransi \n " +
                "FROM mt_simrs_permintaan_resep pr\n" +
                "INNER JOIN (\n" +
                "\tSELECT id_detail_checkup, status_periksa, id_jenis_periksa_pasien, id_asuransi FROM it_simrs_header_detail_checkup \n" +
                "\tWHERE id_pelayanan = '"+idPelayanan+"'\n" +
                "\tAND status_periksa = '3'\n" +
                "\tORDER BY last_update DESC \n" +
                ") dc ON dc.id_detail_checkup = pr.id_detail_checkup\n" +
                "WHERE pr.id_pasien = '"+idPasien+"'\n" +
                "AND pr.status = '3'\n" +
                "ORDER BY tgl_antrian DESC LIMIT 1";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<PermintaanResep> obatDetailList = new ArrayList<>();
        if (list.size() > 0){
            for (Object[] obj : list){
                PermintaanResep obatDetail = new PermintaanResep();
                obatDetail.setIdPermintaanResep(obj[0].toString());
                obatDetail.setIdApprovalObat(obj[1].toString());
                obatDetail.setIdPasien(obj[2].toString());
                obatDetail.setIdDetailCheckup(obj[3].toString());
                obatDetail.setTglAntrian((Timestamp) obj[4]);
                obatDetail.setTujuanPelayanan(obj[5].toString());
                obatDetail.setBranchId(obj[6].toString());
                obatDetail.setIdJenisPeriksa(obj[7].toString());
                obatDetail.setIdAsuransi(obj[8] == null ? null : obj[8].toString());
                obatDetail.setStTglAntrian(obatDetail.getTglAntrian().toString());
                obatDetailList.add(obatDetail);
            }
        }
        return obatDetailList;
    }

    public List<PermintaanResep> listResepPasienByIdApproval(String idApproval){

        String SQL = "SELECT \n" +
                "pr.id_permintaan_resep,\n" +
                "pr.id_approval_obat,\n" +
                "pr.id_pasien,\n" +
                "pr.id_detail_checkup,\n" +
                "pr.tgl_antrian,\n" +
                "pr.tujuan_pelayanan,\n" +
                "pr.branch_id,\n" +
                "dc.id_jenis_periksa_pasien \n," +
                "dc.id_asuransi \n" +
                "FROM mt_simrs_permintaan_resep pr\n" +
                "INNER JOIN (" +
                "SELECT id_detail_checkup, id_jenis_periksa_pasien, id_asuransi FROM it_simrs_header_detail_checkup" +
                ") dc ON dc.id_detail_checkup = pr.id_detail_checkup\n" +
                "WHERE pr.id_approval_obat = '"+idApproval+"'\n" +
                "ORDER BY pr.tgl_antrian DESC LIMIT 1";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<PermintaanResep> obatDetailList = new ArrayList<>();
        if (list.size() > 0){
            for (Object[] obj : list){
                PermintaanResep obatDetail = new PermintaanResep();
                obatDetail.setIdPermintaanResep(obj[0].toString());
                obatDetail.setIdApprovalObat(obj[1].toString());
                obatDetail.setIdPasien(obj[2].toString());
                obatDetail.setIdDetailCheckup(obj[3].toString());
                obatDetail.setTglAntrian((Timestamp) obj[4]);
                obatDetail.setTujuanPelayanan(obj[5].toString());
                obatDetail.setBranchId(obj[6].toString());
                obatDetail.setIdJenisPeriksa(obj[7].toString());
                obatDetail.setIdAsuransi(obj[8] == null ? null : obj[8].toString());
                obatDetail.setStTglAntrian(obatDetail.getTglAntrian().toString());
                obatDetailList.add(obatDetail);
            }
        }
        return obatDetailList;
    }

    public List<TransaksiObatDetail> listIsiResep(PermintaanResep bean){

        String SQL = "SELECT \n" +
                "ob.nama_obat,\n" +
                "tr.keterangan,\n" +
                "trb.qty_approve,\n" +
                "orac.id as id_racik,\n" +
                "orac.nama as nama_racik,\n" +
                "orac.qty,\n" +
                "orac.kemasan,\n" +
                "orac.signa,\n" +
                "ob.id_obat,\n" +
                "orac.warna \n" +
                "FROM mt_simrs_transaksi_obat_detail tr\n" +
                "INNER JOIN (\n" +
                "\tSELECT id_transaksi_obat_detail, SUM (qty_approve) as qty_approve\n" +
                "\tFROM\n" +
                "\tmt_simrs_transaksi_obat_detail_batch \n" +
                "\tGROUP BY id_transaksi_obat_detail\n" +
                ") trb ON trb.id_transaksi_obat_detail = tr.id_transaksi_obat_detail\n" +
                "INNER JOIN im_simrs_header_obat ob ON ob.id_obat = tr.id_obat\n" +
                "LEFT JOIN it_simrs_obat_racik orac ON orac.id = tr.id_racik\n" +
                "WHERE tr.id_approval_obat = '"+bean.getIdApprovalObat()+"' \n" +
                "ORDER BY tr.id_transaksi_obat_detail\n";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        List<TransaksiObatDetail> obatDetails = new ArrayList<>();

        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setIdDetailCheckup(bean.getIdDetailCheckup());
        obatPoli.setIdPelayanan(bean.getTujuanPelayanan());
        obatPoli.setJenisPasien(bean.getIdJenisPeriksa());
        obatPoli.setBranchId(bean.getBranchId());

        if (list.size() > 0){
            for (Object[] obj : list){
                TransaksiObatDetail obatDetail = new TransaksiObatDetail();
                obatDetail.setNamaObat(obj[0].toString());
                obatDetail.setKeterangan(obj[1] == null ? "" : obj[1].toString());
                obatDetail.setQtyApprove(obj[2] == null ? null : new BigInteger(obj[2].toString()));
                obatDetail.setIdRacik(obj[3] == null ? null : obj[3].toString());
                obatDetail.setNamaRacik(obj[4] == null ? "" : obj[4].toString());
                obatDetail.setQtyRacik(obj[5] == null ? null : new Integer(obj[5].toString()));
                obatDetail.setKemasan(obj[6] == null ? "" : obj[6].toString());
                obatDetail.setSignaRacik(obj[7] == null ? "" : obj[7].toString());
                obatDetail.setIdObat(obj[8] == null ? "" : obj[8].toString());
                obatDetail.setWarna(obj[9] == null ? "" : obj[9].toString());

//                if (!"".equalsIgnoreCase(obatDetail.getIdObat())){
//                    HargaObatPerKonsumen hargaObatPerKonsumen = getDataHargaPerKonsumen(obatDetail.getIdObat(), bean.getBranchId(), bean.getIdJenisPeriksa(), bean.getIdAsuransi());
//                    if (hargaObatPerKonsumen != null && hargaObatPerKonsumen.getHargaJual() != null){
//                        obatDetail.setHarga(new BigInteger(hargaObatPerKonsumen.getHargaJual().toString()));
//                    }
//                }
                obatDetails.add(obatDetail);
            }
        }

        return obatDetails;
    }

    public String getTipePelayananOfIdDetailCheckup(String idDetailCheckup){
        String SQL = "SELECT \n" +
                "hp.tipe_pelayanan\n" +
                "FROM im_simrs_header_pelayanan hp \n" +
                "INNER JOIN im_simrs_pelayanan p ON p.id_header_pelayanan = hp.id_header_pelayanan\n" +
                "INNER JOIN it_simrs_header_detail_checkup hdc ON hdc.id_pelayanan = p.id_pelayanan\n" +
                "WHERE hdc.id_detail_checkup = '"+idDetailCheckup+"'";

        List<Object> objectList = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (objectList.size() > 0){
            return objectList.get(0).toString();
        }
        return null;
    }

    public List<ObatPoli> getIdObatGroupPoli(ObatPoli bean){
        List<ObatPoli> obatPoliList = new ArrayList<>();
        String queryJenisObat = "";

        //if(idPelayanan != null && !"".equalsIgnoreCase(idPelayanan) && branchId != null && !"".equalsIgnoreCase(branchId)){
        if(bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan()) && bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){

            String flag = "";
            String whereQuery = "";
//            if("bpjs".equalsIgnoreCase(flagBpjs) || "bpjs_rekanan".equalsIgnoreCase(flagBpjs)){
            if("bpjs".equalsIgnoreCase(bean.getJenisPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getJenisPasien())){
                flag = "WHERE flag_bpjs = 'Y' \n";
//                whereQuery = "WHERE c.harga_jual_khusus_bpjs > 0 \n" +
//                        "AND c.harga_jual_umum_bpjs > 0 \n";
            }else{
                flag = "WHERE flag_bpjs != 'Y' \n";
//                whereQuery = "WHERE c.harga_jual > 0 \n" +
//                        "AND c.harga_jual_umum > 0 \n";
            }

            String whereObat = "";
            if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
                whereObat = "WHERE a.id_obat = '"+bean.getIdObat()+"' \n";
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
                    "a.flag_kronis,\n" +
                    "c.harga_jual,\n" +
                    "c.harga_jual_umum,\n" +
                    "c.harga_jual_khusus_bpjs,\n" +
                    "c.harga_jual_umum_bpjs\n" +
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
                    "\t\t\tAND id_barang is not null\n" +
                    "\t\t) b ON b.id_obat = a.id_obat\n" +
                    "\t\tINNER JOIN mt_simrs_obat_poli ob ON ob.id_barang = b.id_barang\n" + whereObat +
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
                    "LEFT JOIN mt_simrs_harga_obat c ON a.id_obat = c.id_obat\n" +
                    "LEFT JOIN (\n" +
                    "\tSELECT\n" +
                    "\tid_obat,\n" +
                    "\tid_jenis_obat\n" +
                    "\tFROM im_simrs_obat_gejala \n" +
                    ")d ON d.id_obat = b.id_obat \n" + whereQuery + queryJenisObat +
                    "GROUP BY \n" +
                    "b.id_pelayanan,\n" +
                    "a.id_obat,\n" +
                    "a.nama_obat,\n" +
                    "b.box,\n" +
                    "b.lembar,\n" +
                    "b.biji,\n" +
                    "a.lembar_per_box,\n" +
                    "a.biji_per_lembar,\n" +
                    "a.flag_kronis,\n" +
                    "c.harga_jual,\n" +
                    "c.harga_jual_umum,\n" +
                    "c.harga_jual_khusus_bpjs,\n" +
                    "c.harga_jual_umum_bpjs \n";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPelayanan", bean.getIdPelayanan())
                    .setParameter("branchId", bean.getBranchId())
                    .list();

            Boolean cekKhusus = cekIsKhusus(bean.getIdDetailCheckup());

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
//
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
        if(results.size() > 0){
            res = true;
        }
        return res;
    }

    private HeaderCheckup getHeaderCheckupData(String idHeaderCheckup){

        String SQL = "SELECT \n" +
                "id_detail_checkup, \n" +
                "id_asuransi,\n" +
                "id_jenis_periksa_pasien\n" +
                "FROM \n" +
                "it_simrs_header_detail_checkup\n" +
                "WHERE id_asuransi is not null\n" +
                "AND id_detail_checkup = '"+idHeaderCheckup+"'";

        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            Object[] obj = list.get(0);
            HeaderCheckup headerCheckup = new HeaderCheckup();
            headerCheckup.setIdDetailCheckup(obj[0].toString());
            headerCheckup.setIdAsuransi(obj[1].toString());
            headerCheckup.setIdJenisPeriksaPasien(obj[2].toString());
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

    private HargaObatPerKonsumen getDataHargaPerKonsumen(String idObat, String branchId, String jenisKonsumen, String idRekanan){

        String sqlRekanan = "";
        if (idRekanan != null && !"".equalsIgnoreCase(idRekanan)){
            sqlRekanan = "AND hopk.id_rekanan = '"+idRekanan+"' \n";
        }

        // 2021-07-01, Sigit
        // set jenis konsumen ke default umum / bpjs jika tidak ditemukan jenis konsumen tersebut pada harga per konsumen
        jenisKonsumen = changeToUmumOrBpjsIfNotAvailableYetInHargaObatPerkonsumen(idObat, branchId, jenisKonsumen, idRekanan);

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
        else
            return new BigDecimal(obj.toString());
    }

}
