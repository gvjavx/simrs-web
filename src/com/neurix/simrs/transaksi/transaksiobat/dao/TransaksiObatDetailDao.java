package com.neurix.simrs.transaksi.transaksiobat.dao;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.obatracik.model.ObatRacik;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class TransaksiObatDetailDao extends GenericDao<ImtSimrsTransaksiObatDetailEntity, String> {
    @Override
    protected Class<ImtSimrsTransaksiObatDetailEntity> getEntityClass() {
        return ImtSimrsTransaksiObatDetailEntity.class;
    }

    @Override
    public List<ImtSimrsTransaksiObatDetailEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImtSimrsTransaksiObatDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_transaksi_obat_detail")!=null) {
                criteria.add(Restrictions.eq("idTransaksiObatDetail", (String) mapCriteria.get("id_transaksi_obat_detail")));
            }
            if (mapCriteria.get("id_approval_obat")!=null) {
                criteria.add(Restrictions.ilike("idApprovalObat", (String)mapCriteria.get("id_approval_obat")));
            }
            if (mapCriteria.get("id_obat")!=null) {
                criteria.add(Restrictions.eq("idObat", (String) mapCriteria.get("id_obat")));
            }
            if (mapCriteria.get("qty")!=null) {
                criteria.add(Restrictions.eq("qty", mapCriteria.get("qty")));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
            if(mapCriteria.get("flag_diterima") != null){
                criteria.add(Restrictions.eq("flagDiterima", mapCriteria.get("flag_diterima")));
            }
            if(mapCriteria.get("flag_not_r") != null){
                criteria.add(Restrictions.ne("flagDiterima", mapCriteria.get("flag_not_r")));
            }
        }

        // Order by
        criteria.addOrder(Order.asc("idObat"));

        List<ImtSimrsTransaksiObatDetailEntity> results = criteria.list();

        return results;
    }

    public List<ImtSimrsTransaksiObatDetailEntity> getListEntityTransObatDetails(TransaksiObatDetail bean){

        String idTransaksi          = "%";
        String idApprovalObat       = "%";
        String idPermintaanResep    = "%";
        String flag                 = "%";
        String branchId             = "%";
        String tipePermintaan       = "001";
        String isOrder              = "";
        String isRacik              = "";

        if (bean.getIdTransaksiObatDetail() != null && !"".equalsIgnoreCase(bean.getIdTransaksiObatDetail())){
            idTransaksi = bean.getIdTransaksiObatDetail();
        }

        if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())){
            idApprovalObat = bean.getIdApprovalObat();
        }

        if (bean.getIdPermintaanResep() != null && !"".equalsIgnoreCase(bean.getIdPermintaanResep())){
            idPermintaanResep = bean.getIdPermintaanResep();
        }

        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            flag = bean.getFlag();
        }

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branchId = bean.getBranchId();
        }

        if (bean.getTipePermintaan() != null && !"".equalsIgnoreCase(bean.getTipePermintaan())){
            tipePermintaan = bean.getTipePermintaan();
        }

        if (bean.getIsOrder() != null && !"".equalsIgnoreCase(bean.getIsOrder())){
            isOrder = "ORDER BY tod.id_transaksi_obat_detail, tod.id_racik ASC";
        }

        String SQL = "SELECT \n" +
                "tod.id_transaksi_obat_detail,\n" +
                "tod.id_approval_obat,\n" +
                "tod.id_obat,\n" +
                "tod.qty,\n" +
                "tod.flag,\n" +
                "tod.action,\n" +
                "tod.created_date,\n" +
                "tod.created_who,\n" +
                "tod.last_update,\n" +
                "tod.last_update_who,\n" +
                "tod.jenis_satuan,\n" +
                "tod.flag_verifikasi,\n" +
                "tod.qty_approve,"+
                "tod.flag_racik,\n" +
                "tod.id_racik,\n" +
                "tod.nama_racik,\n" +
                "tod.keterangan,\n" +
                "tod.jenis_resep,\n" +
                "tod.hari_kronis\n" +
                "FROM mt_simrs_transaksi_obat_detail tod\n" +
                "INNER JOIN \n" +
                "(\n" +
                "\tSELECT \n" +
                "\tid_approval_obat,\n" +
                "\tbranch_id \n" +
                "\tFROM mt_simrs_approval_transaksi_obat \n" +
                "\tWHERE tipe_permintaan = :tipe \n" +
                ") ato ON ato.id_approval_obat = tod.id_approval_obat\n" +
                "INNER JOIN mt_simrs_permintaan_resep pr ON pr.id_approval_obat = ato.id_approval_obat\n" +
                "WHERE tod.flag LIKE :flag \n" +
                "AND ato.branch_id LIKE :branchId \n" +
                "AND tod.id_approval_obat LIKE :idApprovalObat \n" +
                "AND tod.id_transaksi_obat_detail LIKE :idTransaksi \n" +
                "AND pr.id_permintaan_resep LIKE :idPermintaanResep \n" + isOrder;

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idTransaksi", idTransaksi)
                .setParameter("idApprovalObat", idApprovalObat)
                .setParameter("idPermintaanResep", idPermintaanResep)
                .setParameter("flag", flag)
                .setParameter("branchId", branchId)
                .setParameter("tipe", tipePermintaan)
                .list();

        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();

        if (results.size() > 0) {
            ImtSimrsTransaksiObatDetailEntity obatDetailEntity;
            for (Object[] obj : results) {
                obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
                obatDetailEntity.setIdTransaksiObatDetail(obj[0].toString());
                obatDetailEntity.setIdApprovalObat(obj[1].toString());
                obatDetailEntity.setIdObat(obj[2].toString());
                obatDetailEntity.setQty((BigInteger) obj[3]);
                obatDetailEntity.setFlag(obj[4].toString());
                obatDetailEntity.setAction(obj[5].toString());
                obatDetailEntity.setCreatedDate((Timestamp) obj[6]);
                obatDetailEntity.setCreatedWho(obj[7].toString());
                obatDetailEntity.setLastUpdate((Timestamp)obj[8]);
                obatDetailEntity.setLastUpdateWho(obj[9].toString());
                obatDetailEntity.setJenisSatuan(obj[10].toString());
                obatDetailEntity.setFlagVerifikasi(obj[11] == null ? "" : obj[11].toString());
                obatDetailEntity.setQtyApprove(obj[12] == null ? null : (BigInteger) obj[12]);
                obatDetailEntity.setFlagRacik(obj[13] == null ? "" : obj[13].toString());
                obatDetailEntity.setIdRacik(obj[14] == null ? "" : obj[14].toString());
                obatDetailEntity.setNamaRacik(obj[15] == null ? "" : obj[15].toString());
                obatDetailEntity.setKeterangan(obj[16] == null ? "" : obj[16].toString());
                obatDetailEntity.setJenisResep(obj[17] == null ? "" : obj[17].toString());
                obatDetailEntity.setHariKronis(obj[18] == null ? null : (Integer) obj[18]);
                obatDetailEntities.add(obatDetailEntity);
            }
        }

        return obatDetailEntities;
    }

    public List<ImtSimrsTransaksiObatDetailEntity> getListEntityTransObatDetailsReqUnit(TransaksiObatDetail bean){

        String idTransaksi          = "%";
        String idApprovalObat       = "%";
        String idPermintaanResep    = "%";
        String flag                 = "%";
        String branchId             = "%";
        String tipePermintaan       = "%";

        if (bean.getIdTransaksiObatDetail() != null && !"".equalsIgnoreCase(bean.getIdTransaksiObatDetail())){
            idTransaksi = bean.getIdTransaksiObatDetail();
        }

        if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())){
            idApprovalObat = bean.getIdApprovalObat();
        }

        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            flag = bean.getFlag();
        }

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branchId = bean.getBranchId();
        }

        if (bean.getTipePermintaan() != null && !"".equalsIgnoreCase(bean.getTipePermintaan())){
            tipePermintaan = bean.getTipePermintaan();
        }

        String SQL = "\n" +
                "SELECT \n" +
                "tod.id_transaksi_obat_detail,\n" +
                "tod.id_approval_obat,\n" +
                "tod.id_obat,\n" +
                "tod.qty,\n" +
                "tod.flag,\n" +
                "tod.action,\n" +
                "tod.created_date,\n" +
                "tod.created_who,\n" +
                "tod.last_update,\n" +
                "tod.last_update_who,\n" +
                "tod.jenis_satuan, tod.flag_verifikasi\n" +
                "FROM mt_simrs_transaksi_obat_detail tod\n" +
                "INNER JOIN \n" +
                "(\n" +
                "\tSELECT \n" +
                "\tid_approval_obat,\n" +
                "\tbranch_id \n" +
                "\tFROM mt_simrs_approval_transaksi_obat \n" +
//                "\tWHERE tipe_permintaan = '001' \n" +
                "\tWHERE tipe_permintaan LIKE :tipe \n" +
//                "\tAND flag = 'Y'\n" +
                ") ato ON ato.id_approval_obat = tod.id_approval_obat\n" +
                "INNER JOIN mt_simrs_permintaan_obat_poli pr ON pr.id_approval_obat = ato.id_approval_obat\n" +
                "WHERE tod.flag LIKE :flag \n" +
                "AND ato.branch_id LIKE :branchId \n" +
                "AND tod.id_approval_obat LIKE :idApprovalObat \n" +
                "AND tod.id_transaksi_obat_detail LIKE :idTransaksi";
//                "AND pr.id_permintaan_resep LIKE :idPermintaanResep ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idTransaksi", idTransaksi)
                .setParameter("idApprovalObat", idApprovalObat)
//                .setParameter("idPermintaanResep", idPermintaanResep)
                .setParameter("flag", flag)
                .setParameter("branchId", branchId)
                .setParameter("tipe", tipePermintaan)
                .list();

        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();

        if (results.size() > 0)
        {
            ImtSimrsTransaksiObatDetailEntity obatDetailEntity;
            for (Object[] obj : results)
            {
                obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();
                obatDetailEntity.setIdTransaksiObatDetail(obj[0].toString());
                obatDetailEntity.setIdApprovalObat(obj[1].toString());
                obatDetailEntity.setIdObat(obj[2].toString());
                obatDetailEntity.setQty((BigInteger) obj[3]);
                obatDetailEntity.setFlag(obj[4].toString());
                obatDetailEntity.setAction(obj[5].toString());
                obatDetailEntity.setCreatedDate((Timestamp) obj[6]);
                obatDetailEntity.setCreatedWho(obj[7].toString());
                obatDetailEntity.setLastUpdate((Timestamp)obj[8]);
                obatDetailEntity.setLastUpdateWho(obj[9].toString());
                obatDetailEntity.setJenisSatuan(obj[10].toString());
                obatDetailEntity.setFlagVerifikasi(obj[11] == null ? "" : obj[11].toString());
                obatDetailEntities.add(obatDetailEntity);
            }
        }

        return obatDetailEntities;
    }

    public List<TransaksiObatDetail> getListTransaksiObatDetailBatchByIdResep(String idPermintaanResep, String idBarang){

        idBarang = idBarang == null || "".equalsIgnoreCase(idBarang) ? "%" : idBarang;
        idPermintaanResep = idPermintaanResep == null || "".equalsIgnoreCase(idPermintaanResep) ? "%" : idPermintaanResep;

        String SQL = "SELECT\n" +
                "a.id_barang,\n" +
                "a.id_transaksi_obat_detail,\n" +
                "b.id_approval_obat,\n" +
                "b.id_obat, \n" +
                "a.qty_approve,\n" +
                "b.jenis_satuan,\n" +
                "b.flag_verifikasi, \n" +
                "a.harga_rata,\n" +
                "a.qty_reture,\n" +
                "a.harga_jual\n" +
                "FROM (SELECT * FROM mt_simrs_transaksi_obat_detail_batch WHERE approve_flag = 'Y') a \n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail b ON b.id_transaksi_obat_detail = a.id_transaksi_obat_detail\n" +
                "INNER JOIN mt_simrs_permintaan_resep c ON c.id_approval_obat = b.id_approval_obat\n" +
                "WHERE c.id_permintaan_resep LIKE :idPermintaanResep \n" +
                "AND a.id_barang LIKE :idbarang ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPermintaanResep", idPermintaanResep)
                .setParameter("idbarang", idBarang)
                .list();

        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

        if (results.size() > 0)
        {
            for (Object[] obj : results)
            {
                TransaksiObatDetail obatDetail = new TransaksiObatDetail();
                obatDetail.setIdBarang(obj[0].toString());
                obatDetail.setIdTransaksiObatDetail(obj[1].toString());
                obatDetail.setIdApprovalObat(obj[2].toString());
                obatDetail.setIdObat(obj[3].toString());
                obatDetail.setQty((BigInteger) obj[4]);
                obatDetail.setJenisSatuan(obj[5].toString());
                obatDetail.setFlagVerifikasi(obj[6] == null ? "" : obj[6].toString());
                obatDetail.setHargaRata(obj[7] == null ? new BigDecimal(0) : (BigDecimal) obj[7]);
                obatDetail.setQtyReture(obj[8] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[8]);
                obatDetail.setHargaJual(obj[9] == null ? new BigDecimal(0) : (BigDecimal) obj[9]);
                obatDetailList.add(obatDetail);
            }
        }

        return obatDetailList;
    }


    public List<PermintaanResep> getListResepPasien(PermintaanResep bean){

        String isUmum        = "%";
        String idTujuan      = "%";
        String branchId      = "%";
        String idResep       = "%";
        String idDetil       = "%";
        String nama          = "%";
        String status        = "%";
        String flag          = "%";

        if (bean.getIsUmum() != null && !"".equalsIgnoreCase(bean.getIsUmum())){
            isUmum = bean.getIsUmum();
        }
        if (bean.getTujuanPelayanan() != null && !"".equalsIgnoreCase(bean.getTujuanPelayanan())){
            idTujuan = bean.getTujuanPelayanan();
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branchId = bean.getBranchId();
        }
        if (bean.getIdPermintaanResep() != null && !"".equalsIgnoreCase(bean.getIdPermintaanResep())){
            idResep = bean.getIdPermintaanResep();
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            idDetil = bean.getIdDetailCheckup();
        }
        if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())){
            nama = "%"+bean.getNamaPasien()+"%";
        }
        if (bean.getStatus() != null && !"".equalsIgnoreCase(bean.getStatus())){
            status = bean.getStatus();
        }
        String telemdic = "";
        if (bean.getIsTelemedic() != null && "Y".equalsIgnoreCase(bean.getIsTelemedic())){
            telemdic = " AND b.id_transaksi_online is NOT NULL \n";
        } else if (bean.getIsTelemedic() != null && "N".equalsIgnoreCase(bean.getIsTelemedic())){
            telemdic = " AND b.id_transaksi_online is NULL \n";
        }

        String SQL = "SELECT \n" +
                "a.id_permintaan_resep, \n" +
                "a.id_detail_checkup, \n" +
                "c.nama, \n" +
                "d.keterangan, \n" +
                "a.id_approval_obat, \n" +
                "b.id_jenis_periksa_pasien, \n" +
                "a.flag, \n" +
                "a.id_transaksi_online, \n" +
                "a.ttd_pasien, \n" +
                "a.ttd_apoteker,\n" +
                "c.no_checkup,\n" +
                "c.id_pasien,\n" +
                "c.jenis_kelamin,\n" +
                "c.tgl_lahir,\n"+
                "e.nama_pelayanan,\n"+
                "e.tipe_pelayanan\n" +
                "FROM mt_simrs_permintaan_resep a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                "INNER JOIN im_simrs_status_pasien d ON a.status = d.id_status_pasien\n" +
                "INNER JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "b.tipe_pelayanan\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) e ON b.id_pelayanan = e.id_pelayanan\n"+
                "WHERE a.flag LIKE :flag \n" +
                "AND a.branch_id LIKE :branchId\n" +
                "AND a.is_umum LIKE :isUmum\n" +
                "AND a.id_permintaan_resep LIKE :idResep\n" +
                "AND a.id_detail_checkup LIKE :idDetail\n" +
                "AND c.nama LIKE :nama\n" +
                "AND a.status LIKE :status\n" +
                "AND a.tujuan_pelayanan LIKE :idTujuan\n" + telemdic +
                "ORDER BY a.tgl_antrian DESC";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("isUmum", isUmum)
                .setParameter("idTujuan", idTujuan)
                .setParameter("branchId", branchId)
                .setParameter("idResep", idResep)
                .setParameter("idDetail", idDetil)
                .setParameter("nama", nama)
                .setParameter("status", status)
                .setParameter("flag", flag)
                .list();

        List<PermintaanResep> permintaanResepList = new ArrayList<>();

        String statusName = "";
        if ("0".equalsIgnoreCase(status)){
            statusName = "Antrian";
        } else if ("1".equalsIgnoreCase(status)){
            statusName = "Proses";
        } else if ("3".equalsIgnoreCase(status)){
            statusName = "Selesai";
        }

        if (results.size() > 0) {
            for (Object[] obj : results) {
                PermintaanResep permintaanResep = new PermintaanResep();
                permintaanResep.setIdPermintaanResep(obj[0].toString());
                permintaanResep.setIdDetailCheckup(obj[1].toString());
                permintaanResep.setNamaPasien(obj[2].toString());
                permintaanResep.setIdApprovalObat(obj[4].toString());
                permintaanResep.setStatus(statusName);
                permintaanResep.setIdJenisPeriksa(obj[5].toString());
                permintaanResep.setFlag(obj[6].toString());
                permintaanResep.setKetJenisAntrian(obj[7] == null ? "Resep RS" : "Telemedic");
                if(obj[8] != null){
                    permintaanResep.setTtdPasien(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_PASIEN+obj[8].toString());
                }
                if(obj[9] != null){
                    permintaanResep.setTtdApoteker(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_APOTEKER+obj[9].toString());
                }
                permintaanResep.setNoCheckup(obj[10].toString());
                permintaanResep.setIdPasien(obj[11].toString());
                permintaanResep.setJenisKelamin(obj[12].toString());
                if (obj[13] != null && !"".equalsIgnoreCase(obj[13].toString())) {
                    String tglLahir = new SimpleDateFormat("dd-MM-yyyy").format((Date) obj[13]);
                    permintaanResep.setTglLahir(tglLahir);
                    permintaanResep.setUmur(CommonUtil.calculateAge((java.sql.Date) obj[13], true));
                }
                permintaanResep.setNamaPelayanan(obj[14].toString());
                permintaanResep.setKategoriPelayanan(obj[15].toString());
                permintaanResepList.add(permintaanResep);
            }
        }

        return permintaanResepList;
    }

    public List<PermintaanResep> getListResepPasienEresep(PermintaanResep bean){

        String isUmum        = "%";
        String idTujuan      = "%";
        String branchId      = "%";
        String idResep       = "%";
        String idDetil       = "";
        String nama          = "%";
        String status        = "%";
        String flag          = "%";

        if (bean.getIsUmum() != null && !"".equalsIgnoreCase(bean.getIsUmum())){
            isUmum = bean.getIsUmum();
        }
        if (bean.getTujuanPelayanan() != null && !"".equalsIgnoreCase(bean.getTujuanPelayanan())){
            idTujuan = bean.getTujuanPelayanan();
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            branchId = bean.getBranchId();
        }
        if (bean.getIdPermintaanResep() != null && !"".equalsIgnoreCase(bean.getIdPermintaanResep())){
            idResep = bean.getIdPermintaanResep();
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            idDetil = "AND a.id_detail_checkup LIKE '"+bean.getIdDetailCheckup()+"'\n";
        }
        if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())){
            nama = "%"+bean.getNamaPasien()+"%";
        }
        if (bean.getStatus() != null && !"".equalsIgnoreCase(bean.getStatus())){
            status = bean.getStatus();
        }
//        String telemdic = "";
//        if (bean.getIsTelemedic() != null && "Y".equalsIgnoreCase(bean.getIsTelemedic())){
//            telemdic = " AND b.id_transaksi_online is NOT NULL \n";
//        } else if (bean.getIsTelemedic() != null && "N".equalsIgnoreCase(bean.getIsTelemedic())){
//            telemdic = " AND b.id_transaksi_online is NULL \n";
//        }

        String SQL = "SELECT \n" +
                "a.id_permintaan_resep, \n" +
                "a.id_detail_checkup, \n" +
                "f.nama, \n" +
                "d.keterangan, \n" +
                "a.id_approval_obat, \n" +
                "e.id_jenis_periksa_pasien, \n" +
                "a.flag, \n" +
                "a.id_transaksi_online  \n" +
                "FROM mt_simrs_permintaan_resep a\n" +
                "--LEFT JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                "INNER JOIN it_simrs_pembayaran_online c ON c.id = a.id_transaksi_online\n" +
                "INNER JOIN im_simrs_status_pasien d ON a.status = d.id_status_pasien\n" +
                "INNER JOIN it_simrs_antrian_telemedic e ON e.id = c.id_antrian_telemedic\n" +
                "INNER JOIN im_simrs_pasien f ON f.id_pasien = e.id_pasien\n" +
                "WHERE a.flag LIKE :flag\n" +
                "AND a.branch_id LIKE :branchId\n" +
                "AND a.is_umum LIKE :isUmum\n" +
                "AND a.id_permintaan_resep LIKE :idResep\n" + idDetil +
                "AND f.nama LIKE :nama\n" +
                "AND a.status LIKE :status\n" +
                "AND a.tujuan_pelayanan LIKE :idTujuan\n" +
                "ORDER BY a.tgl_antrian DESC";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("isUmum", isUmum)
                .setParameter("idTujuan", idTujuan)
                .setParameter("branchId", branchId)
                .setParameter("idResep", idResep)
                .setParameter("nama", nama)
                .setParameter("status", status)
                .setParameter("flag", flag)
                .list();

        List<PermintaanResep> permintaanResepList = new ArrayList<>();

        String statusName = "";
        if ("0".equalsIgnoreCase(status)){
            statusName = "Antrian";
        } else if ("1".equalsIgnoreCase(status)){
            statusName = "Proses";
        } else if ("3".equalsIgnoreCase(status)){
            statusName = "Selesai";
        }

        if (results.size() > 0)
        {
            PermintaanResep permintaanResep;
            for (Object[] obj : results)
            {
                permintaanResep = new PermintaanResep();
                permintaanResep.setIdPermintaanResep(obj[0].toString());
                permintaanResep.setIdDetailCheckup(obj[1] == null ? "" : obj[1].toString());
                permintaanResep.setNamaPasien(obj[2].toString());
                permintaanResep.setIdApprovalObat(obj[4].toString());
                permintaanResep.setStatus(statusName);
                permintaanResep.setIdJenisPeriksa(obj[5].toString());
                permintaanResep.setFlag(obj[6].toString());
                permintaanResep.setKetJenisAntrian(obj[7] == null ? "Resep RS" : "Telemedic");
                permintaanResepList.add(permintaanResep);
            }
        }

        return permintaanResepList;
    }

    public List<TransaksiObatDetail> getListPembelianObat(String idApproval){

        String SQL = "SELECT a.id_approval_obat, a.id_transaksi_obat_detail, a.id_obat, b.id_barang, b.qty_approve, b.jenis_satuan FROM mt_simrs_transaksi_obat_detail a\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail_batch b ON a.id_transaksi_obat_detail = b.id_transaksi_obat_detail\n" +
                "WHERE a.id_approval_obat = :idApprov \n" +
                "AND a.flag = 'Y'\n" +
                "AND b.flag = 'Y' ORDER BY a.id_obat ASC";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idApprov", idApproval)
                .list();

        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();

        if (results.size() > 0)
        {
            TransaksiObatDetail transaksiObatDetail;
            for (Object[] obj : results)
            {
                transaksiObatDetail = new TransaksiObatDetail();
                transaksiObatDetail.setIdApprovalObat(obj[0].toString());
                transaksiObatDetail.setIdTransaksiObatDetail(obj[1].toString());
                transaksiObatDetail.setIdObat(obj[2].toString());
                transaksiObatDetail.setIdBarang(obj[3].toString());
                transaksiObatDetail.setQtyApprove(new BigInteger(String.valueOf(obj[4].toString())));
                transaksiObatDetail.setJenisSatuan(obj[5].toString());
                transaksiObatDetails.add(transaksiObatDetail);
            }
        }

        return transaksiObatDetails;
    }

    public List<TransaksiObatDetail> getListRiwayatPembelianObat(String idApproval){

        String SQL = "SELECT a.id_approval_obat, a.id_transaksi_obat_detail, a.id_obat, b.id_barang, b.qty_approve, b.jenis_satuan FROM mt_simrs_transaksi_obat_detail a\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail_batch b ON a.id_transaksi_obat_detail = b.id_transaksi_obat_detail\n" +
                "WHERE a.id_approval_obat = :idApprov \n" +
                "AND b.approve_flag = 'Y' ORDER BY a.id_obat ASC";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idApprov", idApproval)
                .list();

        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();

        if (results.size() > 0)
        {
            TransaksiObatDetail transaksiObatDetail;
            for (Object[] obj : results)
            {
                transaksiObatDetail = new TransaksiObatDetail();
                transaksiObatDetail.setIdApprovalObat(obj[0].toString());
                transaksiObatDetail.setIdTransaksiObatDetail(obj[1].toString());
                transaksiObatDetail.setIdObat(obj[2].toString());
                transaksiObatDetail.setIdBarang(obj[3].toString());
                transaksiObatDetail.setQtyApprove(new BigInteger(String.valueOf(obj[4].toString())));
                transaksiObatDetail.setJenisSatuan(obj[5].toString());
                transaksiObatDetails.add(transaksiObatDetail);
            }
        }

        return transaksiObatDetails;
    }

    public List getListOfObatBatchPermintaan(String idApproval, String flagDiterima){

        String strDiterimaFlag = "";
        if (!"".equalsIgnoreCase(flagDiterima)){
            strDiterimaFlag = "\n AND odb.approve_flag = 'Y'";
        }

        String SQL = "SELECT \n" +
                "od.id_approval_obat,\n" +
                "od.id_transaksi_obat_detail,\n" +
                "odb.id_barang,\n" +
                "odb.qty_approve,\n" +
                "odb.jenis_satuan,\n" +
                "CASE WHEN odb.jenis_satuan = 'box' THEN od.average_harga_box WHEN odb.jenis_satuan = 'lembar' THEN od.average_harga_lembar WHEN odb.jenis_satuan = 'biji' THEN od.average_harga_biji ELSE 0 END as harga ,\n" +
                "odb.qty_reture,\n" +
                "odb.harga_rata\n" +
                "FROM mt_simrs_transaksi_obat_detail_batch odb\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail od ON od.id_transaksi_obat_detail = odb.id_transaksi_obat_detail\n" +
                "WHERE od.id_approval_obat = :idApprove\n" +
                "AND odb.status = 'Y'"+strDiterimaFlag;

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idApprove", idApproval)
                .list();

        List<TransaksiObatDetail> trans = new ArrayList<>();
        if (results.size() > 0){

            TransaksiObatDetail obatDetail;
            for (Object[] obj : results){
                obatDetail = new TransaksiObatDetail();
                obatDetail.setIdApprovalObat(obj[0].toString());
                obatDetail.setIdTransaksiObatDetail(obj[1].toString());
                obatDetail.setIdBarang(obj[2].toString());
                obatDetail.setQtyApprove(obj[3] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[3]);
                obatDetail.setJenisSatuan(obj[4].toString());
                BigDecimal harga = obj[5] == null ? new BigDecimal(0) : (BigDecimal) obj[5];
                obatDetail.setQtyReture(obj[6] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[6]);
                obatDetail.setHargaRata(obj[7] == null ? new BigDecimal(0) : (BigDecimal) obj[7]);
                obatDetail.setHarga(new BigInteger(harga.toString()));
                trans.add(obatDetail);
            }
        }
        return trans;
    }

    public Boolean cekObatKronis(String idApproval){

        Boolean response = false;

        if (!"".equalsIgnoreCase(idApproval) && idApproval != null){

            String SQL = "SELECT \n" +
                    "a.id_permintaan_resep, \n" +
                    "b.id_detail_checkup,\n" +
                    "b.is_kronis\n" +
                    "FROM mt_simrs_permintaan_resep a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                    "WHERE a.id_approval_obat = :idApp\n" +
                    "AND b.is_kronis = 'Y'";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idApp", idApproval)
                    .list();

            if (results.size() > 0){
                response = true;
            }
        }

        return response;
    }

    public TransaksiObatDetail getTarifResepApprove(String idApproval){

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();

        if (!"".equalsIgnoreCase(idApproval) && idApproval != null){

            String SQL = "SELECT \n" +
                    "a.id_permintaan_resep, \n" +
                    "a.id_detail_checkup, \n" +
                    "SUM(a.total) as tarif, \n" +
                    "a.id_pelayanan, \n" +
                    "a.id_pasien, \n" +
                    "a.id_jenis_periksa_pasien,\n" +
                    "a.no_checkup, \n" +
                    "a.jenis_resep \n" +
                    "FROM(\n" +
                    "\tSELECT \n" +
                    "\ta.id_permintaan_resep, \n" +
                    "\ta.id_detail_checkup,\n" +
                    "\tb.id_transaksi_obat_detail,\n" +
                    "\tb.id_obat,\n" +
                    "\tb.jenis_satuan,\n" +
                    "\tc.qty,\n" +
                    "\td.harga_jual,\n" +
                    "\t(d.harga_jual * c.qty) as total,\n" +
                    "\te.id_pelayanan,\n" +
                    "\tf.no_checkup,\n" +
                    "\tf.id_pasien,\n" +
                    "\te.id_jenis_periksa_pasien,\n" +
                    "\ta.jenis_resep\n" +
                    "\tFROM mt_simrs_permintaan_resep a\n" +
                    "\tINNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                    "\tINNER JOIN (SELECT id_transaksi_obat_detail, SUM(qty_approve) as qty FROM mt_simrs_transaksi_obat_detail_batch GROUP BY id_transaksi_obat_detail)c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail\n" +
                    "\tINNER JOIN mt_simrs_harga_obat d ON b.id_obat = d.id_obat\n" +
                    "\tINNER JOIN it_simrs_header_detail_checkup e ON a.id_detail_checkup = e.id_detail_checkup\n" +
                    "\tINNER JOIN it_simrs_header_checkup f ON e.no_checkup = f.no_checkup\n" +
                    "\tWHERE a.id_approval_obat = :idApp\n" +
                    "\t)a\n" +
                    "GROUP BY a.id_detail_checkup,\n" +
                    "a.id_permintaan_resep, \n" +
                    "a.id_pelayanan, \n" +
                    "a.id_pasien, \n" +
                    "a.id_jenis_periksa_pasien,\n" +
                    "a.no_checkup, \n" +
                    "a.jenis_resep";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idApp", idApproval)
                    .list();

            if (results.size() > 0){

                Object[] objects = results.get(0);
                transaksiObatDetail.setIdPermintaanResep(objects[0] == null ? "" : objects[0].toString());
                transaksiObatDetail.setIdDetailCheckup(objects[1] == null ? "" : objects[1].toString());
                transaksiObatDetail.setTotalHarga(objects[2] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(objects[2].toString()));
                transaksiObatDetail.setIdPelayanan(objects[3] == null ? "" : objects[3].toString());
                transaksiObatDetail.setIdPasien(objects[4] == null ? "" : objects[4].toString());
                transaksiObatDetail.setJenisPeriksaPasien(objects[5] == null ? "" : objects[5].toString());
                transaksiObatDetail.setNoCheckup(objects[6] == null ? "" : objects[6].toString());
                transaksiObatDetail.setJenisResep(objects[7] == null ? "" :objects[7].toString());
            }
        }

        return transaksiObatDetail;
    }

    public TransaksiObatDetail getTotalHargaResepApproveUmum(String idPermintaan){

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();

        if (!"".equalsIgnoreCase(idPermintaan) && idPermintaan != null){

            String SQL = "SELECT \n" +
                    "a.id_permintaan_resep, \n" +
                    "a.id_detail_checkup, \n" +
                    "SUM(a.total) as tarif, \n" +
                    "a.id_pelayanan, \n" +
                    "a.id_pasien, \n" +
                    "a.id_jenis_periksa_pasien,\n" +
                    "a.no_checkup,\n" +
                    "a.jenis_resep\n" +
                    "FROM(\n" +
                    "\tSELECT \n" +
                    "\ta.id_permintaan_resep, \n" +
                    "\ta.id_detail_checkup,\n" +
                    "\tb.id_transaksi_obat_detail,\n" +
                    "\tb.id_obat,\n" +
                    "\tb.jenis_satuan,\n" +
                    "\tc.qty,\n" +
                    "\td.harga_jual,\n" +
                    "\t(d.harga_jual_umum * c.qty) as total,\n" +
                    "\te.id_pelayanan,\n" +
                    "\tf.no_checkup,\n" +
                    "\tf.id_pasien,\n" +
                    "\te.id_jenis_periksa_pasien,\n" +
                    "\tb.jenis_resep\n" +
                    "\tFROM mt_simrs_permintaan_resep a\n" +
                    "\tINNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                    "\tINNER JOIN (SELECT id_transaksi_obat_detail, SUM(qty_approve) as qty FROM mt_simrs_transaksi_obat_detail_batch WHERE approve_flag = 'Y'  GROUP BY id_transaksi_obat_detail)c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail\n" +
                    "\tINNER JOIN mt_simrs_harga_obat d ON b.id_obat = d.id_obat\n" +
                    "\tINNER JOIN it_simrs_header_detail_checkup e ON a.id_detail_checkup = e.id_detail_checkup\n" +
                    "\tINNER JOIN it_simrs_header_checkup f ON e.no_checkup = f.no_checkup\n" +
                    "\tWHERE a.id_permintaan_resep = :idPermin\n" +
                    "\t)a\n" +
                    "GROUP BY a.id_detail_checkup,\n" +
                    "a.id_permintaan_resep, \n" +
                    "a.id_pelayanan, \n" +
                    "a.id_pasien, \n" +
                    "a.id_jenis_periksa_pasien,\n" +
                    "a.no_checkup,\n" +
                    "a.jenis_resep";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPermin", idPermintaan)
                    .list();

            if (results.size() > 0){

                Object[] objects = results.get(0);
                transaksiObatDetail.setIdPermintaanResep(objects[0] == null ? "" : objects[0].toString());
                transaksiObatDetail.setIdDetailCheckup(objects[1] == null ? "" : objects[1].toString());
                transaksiObatDetail.setTotalHarga(objects[2] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(objects[2].toString()));
                transaksiObatDetail.setIdPelayanan(objects[3] == null ? "" : objects[3].toString());
                transaksiObatDetail.setIdPasien(objects[4] == null ? "" : objects[4].toString());
                transaksiObatDetail.setJenisPeriksaPasien(objects[5] == null ? "" : objects[5].toString());
                transaksiObatDetail.setNoCheckup(objects[6] == null ? "" : objects[6].toString());
                transaksiObatDetail.setJenisResep(objects[7] == null ? "" : objects[7].toString());

            }
        }

        return transaksiObatDetail;
    }

    public TransaksiObatDetail getTotalHargaResepApprove(String idPermintaan){

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();

        if (!"".equalsIgnoreCase(idPermintaan) && idPermintaan != null){

            String SQL = "SELECT \n" +
                    "a.id_permintaan_resep, \n" +
                    "a.id_detail_checkup, \n" +
                    "SUM(a.total) as tarif, \n" +
                    "a.id_pelayanan, \n" +
                    "a.id_pasien, \n" +
                    "a.id_jenis_periksa_pasien,\n" +
                    "a.no_checkup,\n" +
                    "a.jenis_resep\n" +
                    "FROM(\n" +
                    "\tSELECT \n" +
                    "\ta.id_permintaan_resep, \n" +
                    "\ta.id_detail_checkup,\n" +
                    "\tb.id_transaksi_obat_detail,\n" +
                    "\tb.id_obat,\n" +
                    "\tb.jenis_satuan,\n" +
                    "\tc.qty,\n" +
                    "\td.harga_jual,\n" +
                    "\t(d.harga_jual * c.qty) as total,\n" +
                    "\te.id_pelayanan,\n" +
                    "\tf.no_checkup,\n" +
                    "\tf.id_pasien,\n" +
                    "\te.id_jenis_periksa_pasien,\n" +
                    "\tb.jenis_resep\n" +
                    "\tFROM mt_simrs_permintaan_resep a\n" +
                    "\tINNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                    "\tINNER JOIN (SELECT id_transaksi_obat_detail, SUM(qty_approve) as qty FROM mt_simrs_transaksi_obat_detail_batch WHERE approve_flag = 'Y'  GROUP BY id_transaksi_obat_detail)c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail\n" +
                    "\tINNER JOIN mt_simrs_harga_obat d ON b.id_obat = d.id_obat\n" +
                    "\tINNER JOIN it_simrs_header_detail_checkup e ON a.id_detail_checkup = e.id_detail_checkup\n" +
                    "\tINNER JOIN it_simrs_header_checkup f ON e.no_checkup = f.no_checkup\n" +
                    "\tWHERE a.id_permintaan_resep = :idPermin\n" +
                    "\t)a\n" +
                    "GROUP BY a.id_detail_checkup,\n" +
                    "a.id_permintaan_resep, \n" +
                    "a.id_pelayanan, \n" +
                    "a.id_pasien, \n" +
                    "a.id_jenis_periksa_pasien,\n" +
                    "a.no_checkup,\n" +
                    "a.jenis_resep";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPermin", idPermintaan)
                    .list();

            if (results.size() > 0){

                Object[] objects = results.get(0);
                transaksiObatDetail.setIdPermintaanResep(objects[0] == null ? "" : objects[0].toString());
                transaksiObatDetail.setIdDetailCheckup(objects[1] == null ? "" : objects[1].toString());
                transaksiObatDetail.setTotalHarga(objects[2] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(objects[2].toString()));
                transaksiObatDetail.setIdPelayanan(objects[3] == null ? "" : objects[3].toString());
                transaksiObatDetail.setIdPasien(objects[4] == null ? "" : objects[4].toString());
                transaksiObatDetail.setJenisPeriksaPasien(objects[5] == null ? "" : objects[5].toString());
                transaksiObatDetail.setNoCheckup(objects[6] == null ? "" : objects[6].toString());
                transaksiObatDetail.setJenisResep(objects[7] == null ? "" : objects[7].toString());

            }
        }

        return transaksiObatDetail;
    }

    public List<TransaksiObatDetail> getListObatResepApprove(String idApprove){

        List<TransaksiObatDetail> transaksiObatDetailList = new ArrayList<>();

        if (idApprove != null && !"".equalsIgnoreCase(idApprove)){

            String SQL = "SELECT\n" +
                    "a.id_permintaan_resep,\n" +
                    "a.id_detail_checkup,\n" +
                    "b.id_transaksi_obat_detail,\n" +
                    "b.id_obat,\n" +
                    "g.nama_obat,\n" +
                    "b.jenis_satuan,\n" +
                    "c.qty,\n" +
                    "d.harga_jual,\n" +
                    "d.harga_jual_umum,\n" +
                    "e.id_pelayanan,\n" +
                    "f.no_checkup,\n" +
                    "f.id_pasien,\n" +
                    "e.id_jenis_periksa_pasien\n" +
                    "FROM mt_simrs_permintaan_resep a\n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                    "INNER JOIN (SELECT id_transaksi_obat_detail, SUM(qty_approve) as qty FROM mt_simrs_transaksi_obat_detail_batch WHERE approve_flag = 'Y'  GROUP BY id_transaksi_obat_detail)c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail\n" +
                    "INNER JOIN mt_simrs_harga_obat d ON b.id_obat = d.id_obat\n" +
                    "LEFT JOIN it_simrs_header_detail_checkup e ON a.id_detail_checkup = e.id_detail_checkup\n" +
                    "LEFT JOIN it_simrs_header_checkup f ON e.no_checkup = f.no_checkup\n" +
                    "INNER JOIN (SELECT id_obat, nama_obat FROM im_simrs_obat GROUP BY id_obat, nama_obat) g ON b.id_obat = g.id_obat\n" +
                    "WHERE a.id_approval_obat = :idApp \n";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idApp", idApprove)
                    .list();

            boolean cekKhusus = checkRekananKhusus(idApprove);

            if (results.size() > 0){

                for (Object[] objects: results){
                    TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                    transaksiObatDetail.setIdPermintaanResep(objects[0] == null ? "" : objects[0].toString());
                    transaksiObatDetail.setIdDetailCheckup(objects[1] == null ? "" : objects[1].toString());
                    transaksiObatDetail.setIdTransaksiObatDetail(objects[2] == null ? "" : objects[2].toString());
                    transaksiObatDetail.setIdObat(objects[3] == null ? "" : objects[3].toString());
                    transaksiObatDetail.setNamaObat(objects[4] == null ? "" : objects[4].toString());
                    transaksiObatDetail.setJenisSatuan(objects[5] == null ? "" : objects[5].toString());
                    transaksiObatDetail.setQty(objects[6] == null ? new BigInteger(String.valueOf(0)) : new BigInteger(objects[6].toString()));

                    BigInteger harga = new BigInteger(String.valueOf("0"));
                    if(cekKhusus){
                        if(objects[7] != null){
                            harga = new BigInteger(objects[7].toString());
                        }
                    }else{
                        if(objects[8] != null){
                            harga = new BigInteger(objects[8].toString());
                        }
                    }
                    transaksiObatDetail.setHarga(harga);
                    transaksiObatDetail.setTotalHarga(harga.multiply(transaksiObatDetail.getQty()));

                    transaksiObatDetail.setIdPelayanan(objects[9] == null ? "" : objects[9].toString());
                    transaksiObatDetail.setNoCheckup(objects[10] == null ? "" : objects[10].toString());
                    transaksiObatDetail.setIdPasien(objects[11] == null ? "" : objects[11].toString());
                    transaksiObatDetail.setJenisPeriksaPasien(objects[12] == null ? "" : objects[12].toString());
                    transaksiObatDetailList.add(transaksiObatDetail);
                }

            }
        }

        return transaksiObatDetailList;
    }

    public List<PermintaanResep> getListNotifResep(String idPelayanan, String branchId){
        List<PermintaanResep> list = new ArrayList<>();
        if(idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)
                && branchId != null && !"".equalsIgnoreCase(branchId)){
            String SQl = "SELECT \n" +
                    "a.id_permintaan_resep,\n" +
                    "a.id_pasien,\n" +
                    "a.tgl_antrian,\n" +
                    "b.nama,\n" +
                    "a.id_detail_checkup\n" +
                    "FROM mt_simrs_permintaan_resep a \n" +
                    "INNER JOIN im_simrs_pasien b ON a.id_pasien = b.id_pasien\n" +
                    "WHERE a.tujuan_pelayanan = :idPel\n" +
                    "AND a.branch_id = :branch\n" +
                    "AND a.status = '0'\n" +
                    "AND a.flag = 'Y'\n" +
                    "AND CAST(a.created_date AS DATE) = CURRENT_DATE \n" +
                    "AND a.is_read IS NULL";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQl)
                    .setParameter("idPel", idPelayanan)
                    .setParameter("branch", branchId)
                    .list();
            if(result.size() > 0){
                for (Object[] obj: result){
                    PermintaanResep permintaanResep = new PermintaanResep();
                    permintaanResep.setIdPermintaanResep(obj[0] == null ? "" : obj[0].toString());
                    permintaanResep.setIdPasien(obj[1] == null ? "" : obj[1].toString());
                    permintaanResep.setTglAntrian(obj[2] == null ? null :(Timestamp)obj[2]);
                    permintaanResep.setNamaPasien(obj[3] == null ? ""  : obj[3].toString());
                    permintaanResep.setIdDetailCheckup(obj[4] == null ? ""  : obj[4].toString());
                    list.add(permintaanResep);
                }
            }
        }
        return list;
    }

    public boolean checkIfRekananKhususByIdResep(String idResep){
        String SQL = "SELECT a.id_detail_checkup, a.id_asuransi, c.nama_rekanan FROM it_simrs_header_detail_checkup a\n" +
                "INNER JOIN mt_simrs_permintaan_resep b ON b.id_detail_checkup = a.id_detail_checkup\n" +
                "INNER JOIN im_simrs_rekanan_ops c ON c.id_rekanan_ops = a.id_asuransi\n" +
                "WHERE b.id_permintaan_resep = :idresep \n" +
                "AND c.tipe IN ('ptpn','khusus'); ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idresep", idResep)
                .list();

        if (results.size() > 0)
            return true;
        return false;
    }

    public Boolean checkRekananKhusus(String idApprove){
        Boolean res = false;
        String SQL = "SELECT\n" +
                "b.id_detail_checkup,\n" +
                "d.id_rekanan_ops\n" +
                "FROM mt_simrs_transaksi_obat_detail a\n" +
                "INNER JOIN mt_simrs_permintaan_resep b ON a.id_approval_obat = b.id_approval_obat\n" +
                "INNER JOIN it_simrs_header_detail_checkup c ON b.id_detail_checkup = c.id_detail_checkup\n" +
                "INNER JOIN im_simrs_rekanan_ops d ON c.id_asuransi = d.id_rekanan_ops \n" +
                "WHERE a.id_approval_obat = :id \n" +
                "AND d.tipe IN ('ptpn', 'khusus')";
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idApprove)
                .list();
        if (results.size() > 0){
            res = true;
        }
        return res;
    }

    public BigInteger sumApproveQty(String idDetail){
        BigInteger res = new BigInteger(String.valueOf("0"));
        if(idDetail != null && !"".equalsIgnoreCase(idDetail)){
            String SQL = "SELECT\n" +
                    "id_transaksi_obat_detail,\n" +
                    "CAST(SUM(qty_approve) AS BIGINT) as approve\n" +
                    "FROM mt_simrs_transaksi_obat_detail_batch\n" +
                    "WHERE id_transaksi_obat_detail = :id\n" +
                    "GROUP BY id_transaksi_obat_detail";
            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idDetail).list();
            if(result.size() > 0){
                Object[] obj = result.get(0);
                if(obj[1] != null){
                    res = (BigInteger) obj[1];
                }
            }
        }
        return res;
    }

    public String getFlagIsRacikInTransaksiObatDetail(String idPermintaan){

        String SQL = "SELECT \n" +
                "a.id_racik \n" +
                "FROM mt_simrs_transaksi_obat_detail a\n" +
                "INNER JOIN mt_simrs_permintaan_resep b ON b.id_approval_obat = a.id_approval_obat\n" +
                "WHERE b.id_permintaan_resep = '"+idPermintaan+"'\n" +
                "AND a.id_racik is not null";

        List<Object> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){
            return "Y";
        }
        return "N";
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_transaksi_obat_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<ObatRacik> getListObatRacik(String idRacik){
        String SQL = "SELECT id, nama, signa, qty, kemasan, warna FROM it_simrs_obat_racik WHERE id = '"+idRacik+"'";
        List<Object[]> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<ObatRacik> obatRacikList = new ArrayList<>();
        if (list.size() > 0){
            for (Object[] obj : list){
                ObatRacik obatRacik = new ObatRacik();
                obatRacik.setId(obj[0] == null ? "" : obj[0].toString());
                obatRacik.setNama(obj[1] == null ? "" : obj[1].toString());
                obatRacik.setSigna(obj[2] == null ? "" : obj[2].toString());
                obatRacik.setQty(new Integer(obj[3] == null ? "0" : obj[3].toString()));
                obatRacik.setKemasan(obj[4] == null ? "" : obj[4].toString());
                obatRacik.setWarna(obj[5] == null ? "" : obj[5].toString());
                obatRacikList.add(obatRacik);
            }
        }

        return obatRacikList;
    }
}