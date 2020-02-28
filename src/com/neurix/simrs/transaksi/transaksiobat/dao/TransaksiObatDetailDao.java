package com.neurix.simrs.transaksi.transaksiobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
                "\tWHERE tipe_permintaan = '001' \n" +
//                "\tAND flag = 'Y'\n" +
                ") ato ON ato.id_approval_obat = tod.id_approval_obat\n" +
                "INNER JOIN mt_simrs_permintaan_resep pr ON pr.id_approval_obat = ato.id_approval_obat\n" +
                "WHERE tod.flag LIKE :flag \n" +
                "AND ato.branch_id LIKE :branchId \n" +
                "AND tod.id_approval_obat LIKE :idApprovalObat \n" +
                "AND tod.id_transaksi_obat_detail LIKE :idTransaksi \n" +
                "AND pr.id_permintaan_resep LIKE :idPermintaanResep ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idTransaksi", idTransaksi)
                .setParameter("idApprovalObat", idApprovalObat)
                .setParameter("idPermintaanResep", idPermintaanResep)
                .setParameter("flag", flag)
                .setParameter("branchId", branchId)
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

    public List<PermintaanResep> getListResepPasien(PermintaanResep bean){

        String isUmum         = "%";
        String idTujuan       = "%";
        String branchId       = "%";
        String idResep        = "%";
        String idDetil       = "%";
        String nama           = "%";
        String status         = "%";
        String flag         = "Y";

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
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            flag = bean.getFlag();
        }

        String SQL = "SELECT a.id_permintaan_resep, a.id_detail_checkup, c.nama, d.keterangan, a.id_approval_obat FROM mt_simrs_permintaan_resep a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                "INNER JOIN im_simrs_status_pasien d ON a.status = d.id_status_pasien\n" +
                "WHERE a.flag = :flag \n" +
                "AND a.branch_id LIKE :branchId\n" +
                "AND a.is_umum LIKE :isUmum\n" +
                "AND a.id_permintaan_resep LIKE :idResep\n" +
                "AND a.id_detail_checkup LIKE :idDetail\n" +
                "AND c.nama LIKE :nama\n" +
                "AND a.status LIKE :status\n" +
                "AND a.tujuan_pelayanan LIKE :idTujuan\n" +
                "ORDER BY a.tgl_antrian ASC";

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
        if ("1".equalsIgnoreCase(status)){
            statusName = "Antrian";
        } else if ("3".equalsIgnoreCase(status)){
            statusName = "Proses";
        } else if ("N".equalsIgnoreCase(flag)){
            statusName = "Selesai";
        }

        if (results.size() > 0)
        {
            PermintaanResep permintaanResep;
            for (Object[] obj : results)
            {
                permintaanResep = new PermintaanResep();
                permintaanResep.setIdPermintaanResep(obj[0].toString());
                permintaanResep.setIdDetailCheckup(obj[1].toString());
                permintaanResep.setNamaPasien(obj[2].toString());
                permintaanResep.setIdApprovalObat(obj[4].toString());
                permintaanResep.setStatus(statusName);
                permintaanResep.setFlag(flag);
                permintaanResep.setIdApprovalObat(obj[4].toString());
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

    public List getListOfObatBatchPermintaan(String idApproval){

        String SQL = "SELECT \n" +
                "od.id_approval_obat,\n" +
                "od.id_transaksi_obat_detail,\n" +
                "odb.id_barang,\n" +
                "odb.qty_approve,\n" +
                "odb.jenis_satuan\n" +
                "FROM mt_simrs_transaksi_obat_detail_batch odb\n" +
                "INNER JOIN mt_simrs_transaksi_obat_detail od ON od.id_transaksi_obat_detail = odb.id_transaksi_obat_detail\n" +
                "WHERE od.id_approval_obat = :idApprove\n" +
                "AND odb.status = 'Y'";

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
                trans.add(obatDetail);
            }
        }
        return trans;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_transaksi_obat_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}